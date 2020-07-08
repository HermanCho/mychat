package cn.edu.scau.dbclub.mychat.service.impl;

import cn.edu.scau.dbclub.mychat.dao.GroupMapper;
import cn.edu.scau.dbclub.mychat.dao.GroupMemMapper;
import cn.edu.scau.dbclub.mychat.dao.GroupMessageMapper;
import cn.edu.scau.dbclub.mychat.dao.UserFriendMapper;
import cn.edu.scau.dbclub.mychat.exception.GroupNotFoundException;
import cn.edu.scau.dbclub.mychat.pojo.do0.Group;
import cn.edu.scau.dbclub.mychat.pojo.do0.GroupMem;
import cn.edu.scau.dbclub.mychat.pojo.do0.GroupMessage;
import cn.edu.scau.dbclub.mychat.service.GroupMemService;
import cn.edu.scau.dbclub.mychat.service.GroupMessageService;
import cn.edu.scau.dbclub.mychat.service.GroupService;
import cn.edu.scau.dbclub.mychat.util.SpringUtil;
import cn.edu.scau.dbclub.mychat.util.redisUtil.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @Description: 群功能
 * @Author: hermanCho
 * @Date: 2020-05-02
 * @Param null:
 * @return: null
 **/

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    GroupMemMapper groupMemMapper;

    @Autowired
    GroupMessageMapper groupMessageMapper;


    @Override
    public Group getGroup(Integer id) {
        Group group = groupMapper.selectGroup(id);
//        if (group == null) throw new GroupNotFoundException("找不到id为：" + id + "的群!");
        return group;
    }


    @Override
    public void addGroup(Integer userId, Group group) {
        group.setMemNum(1);
        group.setMemOnlineNum(1);
        int groupId = groupMapper.insertGroup(group);

        GroupMem groupMem = new GroupMem();
        groupMem.setGroupId(groupId);
        groupMem.setUserId(userId);

        // 获取当前最新的群消息,不确定主键的生成策略是不是从0开始
        // 应该可以直接替换成0的
        GroupMessage groupMessage = groupMessageMapper.selectEarliestGroupMessage(groupId);
        int groupMessageId = groupMessage.getId();
        groupMem.setGroupMsgId(groupMessageId);

        //创建者作为成员插入
        groupMemMapper.insertGroupMem(groupMem);
        groupMapper.insertGroup(group);
    }


    @Override
    public void modifyGroup(Group group) {
        groupMapper.updateGroup(group);
    }


    //------分割线----- redis↓

    /***
     * @Description: 处理在线人数，转移redis的逻辑到Service层，方便代码复用
     * @Author: hermanCho
     * @Date: 2020-05-02
     * @Param null:
     * @return: null
     **/

    //都没用@Value注入

    private static String pregroupOnlineNumKey = "gOnlineNum:";

    //因为过期key无法获取value.
    // 因此用一个copy的key，过期时间比原key更长，通过该key获取
    private static String cpPregroupOnlineNumKey = pregroupOnlineNumKey + "copy";

    //copyKey的过期时间为35min
    private static int cpKeyExpireTime = 60 * 35;


    /***
     * @Description: 从redis中获取群在线人数。
     *              若无，则访问DB，并更新写入redis
     * @Author: hermanCho
     * @Date: 2020-05-06
     * @Param group:
     * @return: java.lang.Integer
     **/
    @Override
    public Integer getGroupMemOnlineNum(Group group) {
        Integer groupMemOnlineNum;
        String key = pregroupOnlineNumKey + group.getId();
        String cpKey = cpPregroupOnlineNumKey + group.getId();
        if (JedisUtil.isExist(key)) {
            //无法直接转换，返回的是object -> String -> Integer
            groupMemOnlineNum = Integer.parseInt(String.valueOf(JedisUtil.getObject(key, String.class)));
            //更新一下copyKey的过期时间
            JedisUtil.setExpire(cpKey, cpKeyExpireTime);
        } else {
            groupMemOnlineNum = this.getGroup(group.getId())
                    .getMemOnlineNum();
            updateGroupMemOnlineNum(group, groupMemOnlineNum);
        }
        return groupMemOnlineNum;
    }


    /***
     * @Description: 更新key和copyKey的值，刷新key过期时间
     *
     * @Author: hermanCho
     * @Date: 2020-05-09
     * @Param group:
     * @Param groupMemOnlineNum:
     * @return: void
     **/
    @Override
    public void updateGroupMemOnlineNum(Group group, Integer groupMemOnlineNum) {
        String key = pregroupOnlineNumKey + group.getId();
        JedisUtil.setObject(key, groupMemOnlineNum);

        String cpKey = cpPregroupOnlineNumKey + group.getId();
        JedisUtil.setObject(cpKey, groupMemOnlineNum, cpKeyExpireTime);
    }


}
