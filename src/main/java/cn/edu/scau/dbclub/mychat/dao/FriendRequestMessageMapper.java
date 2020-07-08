package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.FriendRequestMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendRequestMessageMapper {
    int deleteFriendRequestMessage(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

    int saveFriendRequestMessage(FriendRequestMessage record);

    List<FriendRequestMessage> getFriendRequestMessages(@Param("userId") Integer userId);

    List<FriendRequestMessage> listFriendRequestMessages();

    int updateFriendRequestMessage(FriendRequestMessage record);

    FriendRequestMessage getFriendRequestMessage(@Param("userId") Integer userId, @Param("friendId") Integer friendId);
}