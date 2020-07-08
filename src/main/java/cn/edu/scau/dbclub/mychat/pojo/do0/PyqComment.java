package cn.edu.scau.dbclub.mychat.pojo.do0;

import java.util.Date;

public class PyqComment {
    private Integer id;

    private Integer pyqId;

    private Integer userId;

    private String content;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPyqId() {
        return pyqId;
    }

    public void setPyqId(Integer pyqId) {
        this.pyqId = pyqId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "PyqComment{" +
                "id=" + id +
                ", pyqId=" + pyqId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}