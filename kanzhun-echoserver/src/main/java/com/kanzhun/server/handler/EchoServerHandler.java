package com.kanzhun.server.handler;

import com.kanzhun.model.Header;
import com.kanzhun.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author:xing.yl
 * @date: 19/1/1
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    /*接收请求后的处理类*/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Message fromMessage = (Message) msg;

        System.out.println(fromMessage.getData());
        //此处写接收到客户端请求后的业务逻辑
        String content = "hello world,this is nettyServer";
        Header header = new Header((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 0, "713f17ca614361fb257dc6741332caf2", content.getBytes("UTF-8").length, 1);
        Message message = new Message(header, content);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoServerHandler.channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}