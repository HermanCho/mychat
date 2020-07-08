package cn.edu.scau.dbclub.mychat.pojo.do0;

import java.util.Date;

public class UserCollection {
    private Integer id;

    private Integer collectorId;

    private String content;

    private String location;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Integer collectorId) {
        this.collectorId = collectorId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserCollection{" +
                "id=" + id +
                ", collectorId=" + collectorId +
                ", content='" + content + '\'' +
                ", location='" + location + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}