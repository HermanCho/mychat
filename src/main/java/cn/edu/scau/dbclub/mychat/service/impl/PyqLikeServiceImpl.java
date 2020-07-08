package cn.edu.scau.dbclub.mychat.service.impl;

import cn.edu.scau.dbclub.mychat.dao.PyqLikeMapper;
import cn.edu.scau.dbclub.mychat.pojo.do0.PyqLike;
import cn.edu.scau.dbclub.mychat.service.PyqLikeService;
import cn.edu.scau.dbclub.mychat.util.DateUtil;
import cn.edu.scau.dbclub.mychat.util.redisUtil.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Transactional
public class PyqLikeServiceImpl implements PyqLikeService {

    @Autowired
    PyqLikeMapper pyqLikeMapper;


    @Autowired
    private ThreadPoolExecutor executor;


    @Override
    public PyqLike getPyqLike(Integer id) {
        return pyqLikeMapper.selectPyqLike(id);
    }


    /***
     * @Description: 删除redis中的数据 , 若redis无数据，直接操作DB
     * @Author: hermanCho
     * @Date: 2020-05-09
     * @Param id:
     * @return: void
     **/

    @Override
    public void deletePyqLike(Integer id) {
        PyqLike pyqLike = pyqLikeMapper.selectPyqLike(id);

        Integer userId = pyqLike.getUserId();

        String key = pyqLikeSetKey + pyqLike.getPyqId();
        String cpKey = cpPyqLikeSetKey + pyqLike.getPyqId();

        List<String> members = JedisUtil.zgetMemberAll(key);

        // redis 中有对应数据,直接删除
        if (members != null) {
            String value = userId + ":" + id;
            JedisUtil.zRemMem(key, value);
            JedisUtil.zRemMem(cpKey, value);
        } else {
            // 没数据，说明已写入DB，直接操作DB
            pyqLikeMapper.deletePyqLike(id);
        }

    }


    private static String pyqLikeSetKey = "pyqLike:";

    private static String cpPyqLikeSetKey = pyqLikeSetKey + "copy";

    // 设置pyq过期时间为 24h  ，一般一天之后没人点赞了，比较合理
    private static int KeyExpireTime = 60 * 60 * 24;

    //copyKey的过期时间为25h
    private static int cpKeyExpireTime = 60 * 60 * 25;

    /***
     * @Description: key 为 pyqLike:{pyqId}  , score 为 时间戳timeStamp ,
     *              value 为userId:{redis生成的唯一Id}
     *
     *              todo 考虑能不能直接用唯一id做score？
     *
     * @Author: hermanCho
     * @Date: 2020-05-09
     * @Param pyqLike:
     * @return: void
     **/

    @Override
    public void addPyqLike(PyqLike pyqLike) {

        Date date = new Date();

        // 对应唯一情况：key过期后触发，其余情况不可能拿到id。
        // 此时只需要加入date字段即可，虽然该date不是真正的date，但因为zset已经排序了，先后顺序维持一致即可
        if (pyqLike.getId() != null) {
            pyqLike.setCreateTime(date);
            pyqLikeMapper.insertPyqLike(pyqLike);
        } else {
            Integer primaryKeyId = JedisUtil.getId();
            Integer pyqId = pyqLike.getPyqId();
            String key = pyqLikeSetKey + pyqId;
            List<String> userIds = JedisUtil.zgetMemberAll(key);

            // redis 中有对应数据  ||  DB中没数据，即第一次触发
            if (userIds != null || pyqLikeMapper.selectListPyqLikes(pyqId) == null) {
                Double score = DateUtil.date2Double(date);
                String value = pyqLike.getUserId() + ":" + primaryKeyId;

                JedisUtil.zAddMem(key, score, value);
                JedisUtil.setExpire(key, KeyExpireTime);

                // 同样，处理cpkey
                String cpKey = cpPyqLikeSetKey + value;
                JedisUtil.zAddMem(cpKey, score, value);
                JedisUtil.setExpire(cpKey, cpKeyExpireTime);
            } else { // DB中有数据，而redis没数据。 说明是key过期后点赞，此时选择直接写入DB
                // 采用redis生成的id
                pyqLike.setId(primaryKeyId);
                pyqLike.setCreateTime(date);
                pyqLikeMapper.insertPyqLike(pyqLike);
            }
        }
    }


    /**
     * @Description: 这里存在隐患，DB和redis数据不一致
     * @Author: hermanCho
     * @Date: 2020-05-09
     * @Param pyqId:
     * @return: java.util.List<cn.edu.scau.dbclub.mychat.pojo.do0.PyqLike>
     **/
    @Override
    public List<PyqLike> getPyqLikes(Integer pyqId) {
        String key = pyqLikeSetKey + pyqId;
        String cpKey = cpPyqLikeSetKey + pyqId;
        List<PyqLike> pyqLikes = new ArrayList<>();

        if (JedisUtil.isExist(key)) {
            // 这里返回的是List，不知道能不能顺利接收
            List<String> members = JedisUtil.zgetMemberAll(key);

            for (String str : members) {
                Integer[] temp = JedisUtil.split(str);
                Integer userId = temp[0];
                Integer primaryKeyId = temp[1];

                PyqLike pyqLike = new PyqLike();
                pyqLike.setId(primaryKeyId);
                pyqLike.setPyqId(pyqId);
                pyqLike.setUserId(userId);
                pyqLikes.add(pyqLike);

            }
            //更新一下过期时间
            JedisUtil.setExpire(key, KeyExpireTime);
            JedisUtil.setExpire(cpKey, cpKeyExpireTime);
        } else {
            // 不重新写入redis，默认过期后，直接操作DB
            pyqLikes = pyqLikeMapper.selectListPyqLikes(pyqId);
        }
        return pyqLikes;
    }


}
