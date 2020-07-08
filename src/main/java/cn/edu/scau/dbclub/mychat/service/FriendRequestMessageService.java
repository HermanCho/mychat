package cn.edu.scau.dbclub.mychat.service;

import cn.edu.scau.dbclub.mychat.pojo.do0.FriendRequestMessage;

import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/4/25
 */
public interface FriendRequestMessageService {
    void save(FriendRequestMessage friendRequestMessage);

    void delete(Integer userId, Integer friendId);

    List<FriendRequestMessage> getFriendRequestMessages(Integer userId);

    FriendRequestMessage getFriendRequestMessage(Integer userId,Integer friendId);
}
