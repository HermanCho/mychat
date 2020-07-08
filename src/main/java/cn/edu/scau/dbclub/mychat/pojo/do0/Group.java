package cn.edu.scau.dbclub.mychat.pojo.do0;

public class Group {
    private Integer id;

    private String name;

    private Integer memNum;

    /***
      * @Description:  因为是动态变化的， 要维持这个字段应该消耗很大。
     *                 要实现，应该在登录、下线时，对所有群触发一遍
      * @Author: hermanCho
      * @Date: 2020-05-01
      * @Param null:
      * @return: null
      **/

    private Integer memOnlineNum;

    private String notice;

    private String qrcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMemNum() {
        return memNum;
    }

    public void setMemNum(Integer memNum) {
        this.memNum = memNum;
    }

    public Integer getMemOnlineNum() {
        return memOnlineNum;
    }

    public void setMemOnlineNum(Integer memOnlineNum) {
        this.memOnlineNum = memOnlineNum;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memNum=" + memNum +
                ", memOnlineNum=" + memOnlineNum +
                ", notice='" + notice + '\'' +
                ", qrcode='" + qrcode + '\'' +
                '}';
    }
}