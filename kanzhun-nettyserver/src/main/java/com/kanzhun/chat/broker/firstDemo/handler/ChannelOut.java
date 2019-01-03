package com.kanzhun.chat.broker.firstDemo.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author:xing.yl
 * @date: 18/11/11
 */
@ChannelHandler.Sharable
public class ChannelOut extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        System.out.println("能走到这么");
        super.write(ctx, msg, promise);
    }



}
