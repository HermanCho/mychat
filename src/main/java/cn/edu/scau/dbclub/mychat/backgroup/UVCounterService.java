package cn.edu.scau.dbclub.mychat.backgroup;

import cn.edu.scau.dbclub.mychat.util.redisUtil.JedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

/***
 * @Description: 因为更像Serivce，直接就归类为Service了 ,但这个跟mapper和实现类无关
 * @Author: hermanCho
 * @Date: 2020-05-07
 * @Param null:
 * @return: null
 **/

//todo 不知道@Service会不会出错，有需要时换为@Component
@Service
public class UVCounterService {

//
// @Value("${redis.loginUser.prefixKey}")
//    String loginUserPrefix;


    private static String loginUserPrefix = "USER:LOGIN:";


    public UVCounterService() {

    }


    /***
     * @Description: 用户登录，加入hyperLogLog。
     * @Author: hermanCho
     * @Date: 2020-05-07
     * @Param userId:
     * @return: void
     **/

    public void addLoginUser(Integer userId) {
        HLLUtil.addLoginUser(loginUserPrefix, userId);
    }

    /**
     * 获取周UV
     *
     * @return UV数
     */
    public long getWeeklyUV() {
        List<String> suffixKeys = HLLUtil.getLastDays(new Date(), 7);
        List<String> keys = HLLUtil.addPrefix(suffixKeys, loginUserPrefix);
        return JedisUtil.pfCount(keys.toArray(new String[0]));
    }

    /**
     * 获取日UV
     *
     * @return UV数
     */
    public long getDailyUV() {
        List<String> suffixKeys = HLLUtil.getLastHours(new Date(), 24);
        List<String> keys = HLLUtil.addPrefix(suffixKeys, loginUserPrefix);
        return JedisUtil.pfCount(keys.toArray(new String[0]));
    }

    /**
     * 获取小时UV
     *
     * @return UV数
     */
    public long getHourlyUV() {
        List<String> suffixKeys = HLLUtil.getLastHours(new Date(), 1);
        List<String> keys = HLLUtil.addPrefix(suffixKeys, loginUserPrefix);
        return JedisUtil.pfCount(keys.toArray(new String[0]));
    }
}