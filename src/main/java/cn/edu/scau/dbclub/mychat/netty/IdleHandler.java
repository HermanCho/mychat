package cn.edu.scau.dbclub.mychat.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class IdleHandler extends ChannelInboundHandlerAdapter {

    /**
     * @description: todo 当触发空闲后，断开连接？不断则浪费资源，若断开，收到信息怎么办？
     * @param ctx
     * @param evt
     * @return: void
     * @author: 杜科
     * @date: 2020/4/20
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent)evt;

            if(idleStateEvent.state() == IdleState.READER_IDLE) {
                System.out.println("读空闲事件触发...");
            }
            else if(idleStateEvent.state() == IdleState.WRITER_IDLE) {
                System.out.println("写空闲事件触发...");
            }
            else if(idleStateEvent.state() == IdleState.ALL_IDLE) {
                System.out.println("---------------");
                System.out.println("读写空闲事件触发");
                System.out.println("关闭通道资源");
                ctx.channel().close();
            }
        }
    }
}
