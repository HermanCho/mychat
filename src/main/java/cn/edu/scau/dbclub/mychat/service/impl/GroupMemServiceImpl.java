package cn.edu.scau.dbclub.mychat.service.impl;

import cn.edu.scau.dbclub.mychat.dao.GroupMapper;
import cn.edu.scau.dbclub.mychat.dao.GroupMemMapper;
import cn.edu.scau.dbclub.mychat.dao.GroupMessageMapper;
import cn.edu.scau.dbclub.mychat.pojo.do0.Group;
import cn.edu.scau.dbclub.mychat.pojo.do0.GroupMem;
import cn.edu.scau.dbclub.mychat.pojo.do0.GroupMessage;
import cn.edu.scau.dbclub.mychat.service.GroupMemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
  * @Description:  增加、减少群成员
  * @Author: hermanCho
  * @Date: 2020-05-02
  * @Param null:
  * @return: null
  **/

@Transactional
@Service
public class GroupMemServiceImpl implements GroupMemService {

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    GroupMemMapper groupMemMapper;

    @Autowired
    GroupMessageMapper groupMessageMapper;

    @Override
    public List<Integer> getMemIdsByGroupId(Integer groupId) {
        return groupMemMapper.selectListGroupMems(groupId);
    }

    @Override
    public List<Group> selectGroupsByUserId(Integer userId) {
        return groupMemMapper.selectGroupsByUserId(userId);
    }


    @Override
    public void addGroupMem(Integer userId, Group group) {
        Integer groupId = group.getId();

        GroupMem groupMem = new GroupMem();
        groupMem.setGroupId(groupId);
        groupMem.setUserId(userId);


        // 获取当前最新的群消息
        GroupMessage groupMessage = groupMessageMapper.selectEarliestGroupMessage(groupId);
        int groupMessageId = groupMessage.getId();
        groupMem.setGroupMsgId(groupMessageId);

        groupMemMapper.insertGroupMem(groupMem);

        // 增加并更新群成员人数
//        group.setMemNum(group.getMemNum() + 1);

//        groupMapper.updateGroup(group);

    }

    @Override
    public void removeGroupMem(Integer userId, Group group) {
        int groupId = group.getId();

        GroupMem groupMem = new GroupMem();
        groupMem.setGroupId(groupId);
        groupMem.setUserId(userId);

        groupMemMapper.deleteGroupMem(groupMem);

        // 减少并更新群成员人数
//        group.setMemNum(group.getMemNum() - 1);

//        groupMapper.updateGroup(group);
    }


    @Override
    public void updateGroupMem(Integer userId, Group group) {
        Integer groupId = group.getId();

        GroupMem groupMem = new GroupMem();
        groupMem.setGroupId(groupId);
        groupMem.setUserId(userId);

        // 获取当前最新的群消息,并更新groupMsgId字段
        GroupMessage groupMessage = groupMessageMapper.selectEarliestGroupMessage(groupId);
        int groupMessageId = groupMessage.getId();
        groupMem.setGroupMsgId(groupMessageId);

        groupMemMapper.updateGroupMem(groupMem);


    }
}
