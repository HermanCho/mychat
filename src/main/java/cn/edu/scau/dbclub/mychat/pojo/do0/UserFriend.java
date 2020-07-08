package cn.edu.scau.dbclub.mychat.pojo.do0;

public class UserFriend {
    private Integer userId;

    private Integer friendId;

    private String remark;

    private String label;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    @Override
    public String toString() {
        return "UserFriend{" +
                "userId=" + userId +
                ", friendId=" + friendId +
                ", remark='" + remark + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}