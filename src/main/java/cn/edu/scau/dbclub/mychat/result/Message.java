package cn.edu.scau.dbclub.mychat.result;

import cn.edu.scau.dbclub.mychat.netty.ConnectedMessage;
import cn.edu.scau.dbclub.mychat.pojo.do0.FriendRequestMessage;
import cn.edu.scau.dbclub.mychat.pojo.do0.GroupMessage;
import cn.edu.scau.dbclub.mychat.pojo.do0.P2pMessage;

public class Message {

    // 消息类型 0为p2p信息，1为群组信息，2为连接信息，3为好友申请信息
    private Integer type;

    private P2pMessage p2pMessage;

    private GroupMessage groupMessage;

    private ConnectedMessage connectedMessage;

    private FriendRequestMessage friendRequestMessage;

    // 扩展消息字段
    private Object ext;

    public Message(){}

    public Message(Integer type, P2pMessage p2pMessage, Object ext){
        this.type=type;
        this.p2pMessage=p2pMessage;
        this.ext=ext;
    }

    public Message(Integer type, GroupMessage groupMessage, Object ext){
        this.type=type;
        this.groupMessage=groupMessage;
        this.ext=ext;
    }

    public Message(Integer type, FriendRequestMessage friendRequestMessage, Object ext){
        this.type=type;
        this.friendRequestMessage=friendRequestMessage;
        this.ext=ext;
    }

    public Message(Integer type, ConnectedMessage connectedMessage, Object ext){
        this.type=type;
        this.connectedMessage=connectedMessage;
        this.ext=ext;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public P2pMessage getP2pMessage() {
        return p2pMessage;
    }

    public void setP2pMessage(P2pMessage p2pMessage) {
        this.p2pMessage = p2pMessage;
    }

    public GroupMessage getGroupMessage() {
        return groupMessage;
    }

    public void setGroupMessage(GroupMessage groupMessage) {
        this.groupMessage = groupMessage;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

    public ConnectedMessage getConnectedMessage() {
        return connectedMessage;
    }

    public void setConnectedMessage(ConnectedMessage connectedMessage) {
        this.connectedMessage = connectedMessage;
    }

    public FriendRequestMessage getFriendRequestMessage() {
        return friendRequestMessage;
    }

    public void setFriendRequestMessage(FriendRequestMessage friendRequestMessage) {
        this.friendRequestMessage = friendRequestMessage;
    }


}
