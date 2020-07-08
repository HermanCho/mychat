package cn.edu.scau.dbclub.mychat.pojo.do0;

import java.util.Date;

public class PyqLike {
    private Integer id;

    private Integer userId;

    private Integer pyqId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPyqId() {
        return pyqId;
    }

    public void setPyqId(Integer pyqId) {
        this.pyqId = pyqId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PyqLike{" +
                "id=" + id +
                ", userId=" + userId +
                ", pyqId=" + pyqId +
                ", createTime=" + createTime +
                '}';
    }
}