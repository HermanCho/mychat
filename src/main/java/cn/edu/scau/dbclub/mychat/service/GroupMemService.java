package cn.edu.scau.dbclub.mychat.service;

import cn.edu.scau.dbclub.mychat.pojo.do0.Group;

import java.util.List;

/***
  * @Description:
  * @Author: hermanCho
  * @Date: 2020-05-05
  * @Param null:
  * @return: null
  **/

public interface GroupMemService {

    //返回群组的所有成员
    List<Integer> getMemIdsByGroupId(Integer groupId);


    // 根据用户id，返回其加入的所有组，和上面区分
    List<Group> selectGroupsByUserId(Integer userId);

    void addGroupMem(Integer userId, Group group);

    void removeGroupMem(Integer userId, Group group);

    /***
     * @Description:  本方法唯一作用就是更新群成员的信息，即 groupMsgId。
     * @Author: hermanCho
     * @Date: 2020-05-05
     * @Param userId:
     * @Param group:
     * @return: void
     **/
    void updateGroupMem(Integer userId, Group group);
}
