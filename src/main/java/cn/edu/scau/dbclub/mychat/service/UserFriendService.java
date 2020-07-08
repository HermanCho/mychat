package cn.edu.scau.dbclub.mychat.service;

import cn.edu.scau.dbclub.mychat.pojo.do0.User;
import cn.edu.scau.dbclub.mychat.pojo.do0.UserFriend;
import cn.edu.scau.dbclub.mychat.result.Message;

import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/4/21
 */
public interface UserFriendService {
    List<User> getFriendsByUserId(Integer userId);

    void save(UserFriend userFriend);

    void applyFriend(Message friendRequestMessage);

    void delete(Integer userId, Integer friendId);
}
