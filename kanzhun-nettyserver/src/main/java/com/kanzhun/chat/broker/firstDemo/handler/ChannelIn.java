package com.kanzhun.chat.broker.firstDemo.handler;

import com.kanzhun.chat.broker.firstDemo.session.SessionManage;
import com.kanzhun.chat.broker.firstDemo.session.model.ChannelSession;
import io.netty.channel.*;
import io.netty.util.Attribute;

/**
 * @author:xing.yl
 * @date: 18/11/11
 */
@ChannelHandler.Sharable
public class ChannelIn extends ChannelInboundHandlerAdapter {

    private SessionManage sessionManage;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(msg);

        Attribute<String> attr = ctx.channel().attr(ChannelSession.ATTR_KEY_CONNECTION_ID);
        if(attr.get()==null){
            sessionManage.addSession(ctx.channel());
        }
        sessionManage.sendOtherSession(ctx.channel(),msg);
//        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("連接上了");

        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {


        System.out.println("出事了");
        final ChannelFuture f = ctx.writeAndFlush("close");
        f.addListener(ChannelFutureListener.CLOSE);

    }

    public void setSessionManage(SessionManage sessionManage) {
        this.sessionManage = sessionManage;
    }
}


