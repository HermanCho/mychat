package cn.edu.scau.dbclub.mychat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class WebsocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // websocket通过http协议来升级，需要有http的编解码器
        pipeline.addLast(new HttpServerCodec());
        // 对写大数据流的支持，如发送大文件，大文件传输时，需要复杂的状态管理，而ChunkedWriteHandler实现这个功能。
        pipeline.addLast(new ChunkedWriteHandler());
        // 添加对HTTP请求和响应的聚合器:只要使用Netty进行Http编程都需要使用
        // 对HttpMessage进行聚合，聚合成FullHttpRequest或者FullHttpResponse
        /**
         * 当我们用POST方式请求服务器的时候，对应的参数信息是保存在message body中的,
         * 如果只是单纯的用HttpServerCodec是无法完全的解析Http POST请求的，因为HttpServerCodec只能获取uri中参数，
         * 所以需要加上HttpObjectAggregator
         */
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //一登陆就拿到连接
//        pipeline.addLast(new IdleStateHandler(4, 8, 12));
//        pipeline.addLast(new IdleHandler());
        // 添加自定义的handler
        pipeline.addLast(new ChatHandler());

    }
}
