package cn.edu.scau.dbclub.mychat.service.impl;

import cn.edu.scau.dbclub.mychat.dao.UserFriendMapper;
import cn.edu.scau.dbclub.mychat.netty.ChatHandler;
import cn.edu.scau.dbclub.mychat.pojo.do0.User;
import cn.edu.scau.dbclub.mychat.pojo.do0.UserFriend;
import cn.edu.scau.dbclub.mychat.result.Message;
import cn.edu.scau.dbclub.mychat.service.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/4/25
 */
@Service
@Transactional
public class UserFriendServiceImpl implements UserFriendService {

    @Autowired
    UserFriendMapper userFriendMapper;

    @Override
    public List<User> getFriendsByUserId(Integer userId) {
        List<User> friends = userFriendMapper.getFriendsByUserId(userId);
        return friends;
    }

    @Override
    public void save(UserFriend userFriend) {
        userFriendMapper.saveUserFriend(userFriend);
    }

    @Override
    public void delete(Integer userId, Integer friendId) {
        userFriendMapper.deleteUserFriend(userId,friendId);
    }

    @Override
    public void applyFriend(Message friendRequestMessage) {
        //尝试使用netty实时推送，如果对方离线，就把消息存下来
        ChatHandler.handleFriendRequestMsg(friendRequestMessage);
    }
}
