package cn.edu.scau.dbclub.mychat.pojo.do0;

/**
  * @Description: 本类唯一意义就是为下线的人保留信息。
 *                不提供查询信息功能
  * @Author: hermanCho
  * @Date: 2020-05-01
  * @Param null:
  * @return: null
  **/

public class GroupMem {
    private Integer groupId;

    private Integer userId;

    private Integer groupMsgId;

    private String notes;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupMsgId() {
        return groupMsgId;
    }

    public void setGroupMsgId(Integer groupMsgId) {
        this.groupMsgId = groupMsgId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    @Override
    public String toString() {
        return "GroupMem{" +
                "groupId=" + groupId +
                ", userId=" + userId +
                ", groupMsgId=" + groupMsgId +
                ", notes='" + notes + '\'' +
                '}';
    }
}