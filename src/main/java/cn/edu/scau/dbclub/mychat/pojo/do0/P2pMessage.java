package cn.edu.scau.dbclub.mychat.pojo.do0;

import java.util.Date;

public class P2pMessage {
    private Integer id;

    private Integer sendId;

    private Integer receiveId;

    private String location;

    private String content;

    private Date time;

    //其实不需要，读了就删了，存在代表未读
    private Integer isRead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "P2pMessage{" +
                "id=" + id +
                ", sendId=" + sendId +
                ", receiveId=" + receiveId +
                ", location='" + location + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", isRead=" + isRead +
                '}';
    }
}