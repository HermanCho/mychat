package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.Group;
import cn.edu.scau.dbclub.mychat.pojo.do0.GroupMem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
  * @Description:  因为本类不是单列主键，多列构成主键，因此传入的是对象
  * @Author: hermanCho
  * @Date: 2020-05-06
  * @Param null:
  * @return: null
  **/

public interface GroupMemMapper {

    int insertGroupMem(GroupMem groupMem);

    int deleteGroupMem(GroupMem groupMem);

    GroupMem selectGroupMem(@Param("userId") Integer userId,@Param("groupId") Integer groupId);

    int updateGroupMem(GroupMem groupMem);


    // 分隔线，上述是基础的CRUD


    // 根据组id，返回组的所有成员id
    List<Integer> selectListGroupMems(Integer groupId);


    //根据用户id返回所有加入的群组  , 和上面区分
    List<Group> selectGroupsByUserId(Integer userId);

    /***
      * @Description: 返回群组最旧的groupMsgId，这个值对应的信息，群成员全部已读。
     *                 小于该MesId的，就可以删除了
      * @Author: hermanCho
      * @Date: 2020-05-06
      * @Param groupId:
      * @return: int  群组最旧的groupMsgId
      **/

    int selectOldestMessageId(Integer groupId);




}