package cn.edu.scau.dbclub.mychat.netty;

/**
 * @author 杜科
 * @description 连接信息，客户连接上来的时候，发送过来
 * @contact AllenDuke@163.com
 * @date 2020/4/20
 */
public class ConnectedMessage {

    //每当客户连接上来时，告知服务端客户的userId，用于为其绑定channel，如果没有这样的机制，在移除时就不行快速查找
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ConnectedMessage{" +
                "userId=" + userId +
                '}';
    }
}
