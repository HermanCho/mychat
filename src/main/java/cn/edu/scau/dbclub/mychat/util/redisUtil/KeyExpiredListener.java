package cn.edu.scau.dbclub.mychat.util.redisUtil;


import cn.edu.scau.dbclub.mychat.pojo.do0.Group;
import cn.edu.scau.dbclub.mychat.pojo.do0.PyqLike;
import cn.edu.scau.dbclub.mychat.service.GroupService;
import cn.edu.scau.dbclub.mychat.service.PyqLikeService;
import cn.edu.scau.dbclub.mychat.service.impl.GroupServiceImpl;
import cn.edu.scau.dbclub.mychat.service.impl.PyqLikeServiceImpl;
import cn.edu.scau.dbclub.mychat.util.SpringUtil;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/***
 * @Description: 监听过期key
 * @Author: hermanCho
 * @Date: 2020-05-09
 * @Param null:
 * @return: null
 **/

//@Component
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    private static String pregroupOnlineNumKey = "gOnlineNum:";

    private static String cpPregroupOnlineNumKey = pregroupOnlineNumKey + "copy";

    //点赞
    private static String pyqLikeSetKey = "pyqLike:";

    private static String cpPyqLikeSetKey = pyqLikeSetKey + "copy";

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("过期key:" + message.toString());
        String messStr = message.toString();
        if (messStr.startsWith(pregroupOnlineNumKey)) {
            String spiltFromMessage = messStr.substring(messStr.indexOf(":") + 1);
            Integer groupId = Integer.valueOf(spiltFromMessage);

            GroupService groupService = SpringUtil.getBean(GroupServiceImpl.class);

            Group group = groupService.getGroup(groupId);

            String copyKey = String.valueOf(JedisUtil.getObject(cpPregroupOnlineNumKey, String.class));
            //让copyKey一起过期，防止存在隐患
            JedisUtil.setExpire(copyKey, 0);
            if (copyKey == null) System.out.println("报错，copyKey不应为null，这步不可能被执行");
            Integer memOnlineNum = Integer.parseInt(copyKey);
            group.setMemOnlineNum(memOnlineNum);

            groupService.modifyGroup(group);
        }
        //处理点赞
        else if (messStr.startsWith(pyqLikeSetKey)) {
            PyqLikeService pyqLikeService = SpringUtil.getBean(PyqLikeServiceImpl.class);
            String spiltFromMessage = messStr.substring(messStr.indexOf(":") + 1);
            Integer pyqId = Integer.valueOf(spiltFromMessage);

            List<String> members = JedisUtil.zgetMemberAll(cpPyqLikeSetKey);

            // 分离参数，写入DB
            for (String str : members) {
                Integer[] temp = JedisUtil.split(str);
                Integer userId = temp[0];
                Integer primaryKeyId = temp[1];
                PyqLike pyqLike = new PyqLike();
                //带id，唯一情况
                pyqLike.setId(primaryKeyId);
                pyqLike.setPyqId(pyqId);
                pyqLike.setUserId(userId);

                pyqLikeService.addPyqLike(pyqLike);
            }


        }
    }


}

