package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.User;
import java.util.List;

public interface UserMapper {
    int deleteUser(Integer id);

    int saveUser(User record);

    User getUser(Integer id);

    List<User> listUsers();

    int updateUser(User record);

    User getUserByChatNum(String chatNum);
}