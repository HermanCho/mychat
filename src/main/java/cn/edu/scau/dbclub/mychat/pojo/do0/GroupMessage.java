package cn.edu.scau.dbclub.mychat.pojo.do0;

import java.util.Date;

public class GroupMessage {
    private Integer id;

    private Integer groupId;

    private Integer senderId;

    private Date time;

    private String content;

    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    @Override
    public String toString() {
        return "GroupMessage{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", senderId=" + senderId +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}