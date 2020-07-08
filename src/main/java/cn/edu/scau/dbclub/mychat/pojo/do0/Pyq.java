package cn.edu.scau.dbclub.mychat.pojo.do0;

import java.util.Date;

public class Pyq {
    private Integer id;

    private Integer sharerId;

    private String content;

    private String pictureLocation;

    private Date createTime;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSharerId() {
        return sharerId;
    }

    public void setSharerId(Integer sharerId) {
        this.sharerId = sharerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation == null ? null : pictureLocation.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pyq{" +
                "id=" + id +
                ", sharerId=" + sharerId +
                ", content='" + content + '\'' +
                ", pictureLocation='" + pictureLocation + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                '}';
    }
}