package cn.edu.scau.dbclub.mychat.pojo.do0;

import java.util.Date;

/**
 * @author 杜科
 * @description 好友申请信息，也属于单对单
 * @contact AllenDuke@163.com
 * @date 2020/4/25
 */
public class FriendRequestMessage {

    Integer userId;

    Integer friendId;

    String content;

    Date applyTime;

    //未删除即未处理，处理了就删除

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FriendRequestMessage{" +
                "userId=" + userId +
                ", friendId=" + friendId +
                ", content='" + content + '\'' +
                ", applyTime=" + applyTime +
                '}';
    }
}
