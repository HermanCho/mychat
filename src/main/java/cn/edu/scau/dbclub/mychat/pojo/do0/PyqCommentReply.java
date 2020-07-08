package cn.edu.scau.dbclub.mychat.pojo.do0;

import java.util.Date;

public class PyqCommentReply {
    private Integer id;

    private Integer pyqId;

    private Integer replierId;

    private Integer repliederId;

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

    public Integer getReplierId() {
        return replierId;
    }

    public void setReplierId(Integer replierId) {
        this.replierId = replierId;
    }

    public Integer getRepliederId() {
        return repliederId;
    }

    public void setRepliederId(Integer repliederId) {
        this.repliederId = repliederId;
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
        return "PyqCommentReply{" +
                "id=" + id +
                ", pyqId=" + pyqId +
                ", replierId=" + replierId +
                ", repliederId=" + repliederId +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}