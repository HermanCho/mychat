package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.User;
import cn.edu.scau.dbclub.mychat.pojo.do0.UserFriend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFriendMapper {
    int deleteUserFriend(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

    int saveUserFriend(UserFriend record);

    UserFriend getUserFriend(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

    List<UserFriend> listUserFriends();

    int updateUserFriend(UserFriend record);

    List<User> getFriendsByUserId(Integer userId);
}