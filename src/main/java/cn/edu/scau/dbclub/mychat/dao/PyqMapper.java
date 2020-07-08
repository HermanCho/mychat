package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.Pyq;
import java.util.List;

public interface PyqMapper {
    int deletePyq(Integer id);

    int savePyq(Pyq record);

    Pyq getPyq(Integer id);

    List<Pyq> listPyqs();

    int updatePyq(Pyq record);

    //UserFriend与Pyq连表查询
    //select Pyq. from user_friend pyq where pyq.shareId=userId or userId= user_friend.userId and friendId=pyq.shareId and pyq.type=1;
    List<Pyq> getUserAndFriendPyqsByUserId(Integer userId);

    List<Pyq> getPyqsByUserId(Integer userId);
}