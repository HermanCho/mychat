package cn.edu.scau.dbclub.mychat.service;

import cn.edu.scau.dbclub.mychat.pojo.do0.Group;
import org.springframework.transaction.annotation.Transactional;

/***
  * @Description: 群组服务
  * @Author: hermanCho
  * @Date: 2020-05-04
  * @Param null:
  * @return: null
  **/


public interface GroupService {

    Group getGroup(Integer Id);

    // 创建群组,   其实group只有name是有效域，但规范还是用成员传参
    void addGroup(Integer userId, Group group);

    void modifyGroup(Group group);

    //--------      分割线  ↓redis   ---------------
    Integer getGroupMemOnlineNum(Group group);

    void updateGroupMemOnlineNum(Group group, Integer groupMemOnlineNum);


}
