package cn.edu.scau.dbclub.mychat.service;

import cn.edu.scau.dbclub.mychat.pojo.do0.GroupMessage;

import java.util.List;

/***
  * @Description: 群組
  * @Author: hermanCho
  * @Date: 2020-05-06
  * @Param null:
  * @return: null
  **/

public interface GroupMessageService {
    void save(GroupMessage groupMessage);

    List<GroupMessage> getUnReadGroupMessages(Integer userId);

    void deleteGroupMessage(GroupMessage groupMessage);

    void deleteListGroupMessage(List<GroupMessage> groupMessages);

    //删除所有群员都已读的消息
    void deleteAllReadGroupMessage(Integer groupId);
}
