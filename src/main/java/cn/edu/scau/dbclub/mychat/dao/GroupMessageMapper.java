package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.Group;
import cn.edu.scau.dbclub.mychat.pojo.do0.GroupMessage;
import java.util.List;

public interface GroupMessageMapper {


    int insertGroupMessage(GroupMessage groupMessage);

    int deleteGroupMessage(Integer id);

    GroupMessage selectGroupMessage(Integer id);


    //这条应该没有意义，没有提供更改信息的功能，但为了规范还是放着
    int updateGroupMessage(GroupMessage groupMessage);


    // 分割线，上述常规CRUD

    // 返回比id更老的信息
    List<GroupMessage> selectOlderGroupMessage(GroupMessage groupMessage);


    //根据群ID,获取最新的群信息。为了加入群时，定位信息
    GroupMessage selectEarliestGroupMessage(Integer groupId);

    // 根据用户id，返回其未读的所有群信息 todo
    List<GroupMessage> selectUnReadGroupMessagesByUserId(Integer userId);




}