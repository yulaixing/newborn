package com.kanzhun.chat.broker.firstDemo.server;

import com.kanzhun.chat.broker.firstDemo.handler.ChannelIn;
import com.kanzhun.chat.broker.firstDemo.handler.ChannelOut;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author:xing.yl
 * @date: 18/11/10
 */
public class FirstDemoServer {

    private int port;

    private ChannelIn channelIn;

    private ChannelIn channelIn2=new ChannelIn();

    private ChannelOut channelOut;

    private StringDecoder stringDecoder=new StringDecoder();

    private StringEncoder stringEncoder=new StringEncoder();

    public void start(){

        new Thread(() -> {

            NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
            NioEventLoopGroup workGroup = new NioEventLoopGroup();
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_REUSEADDR,true)
                    .childOption(ChannelOption.SO_SNDBUF, 1024 * 1024)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {


                            System.out.println("還有誰");
                            //字符串解码器
                            nioSocketChannel.pipeline().addLast(stringDecoder);
                            nioSocketChannel.pipeline().addLast(channelIn);
                            //字符串编码器
                            nioSocketChannel.pipeline().addLast(stringEncoder);
//                            nioSocketChannel.pipeline().addLast(channelOut);
//                            nioSocketChannel.pipeline().addLast(channelIn2);
                        }
                    });

            try {
                ChannelFuture future = b.bind(port).sync();
                System.out.println("强退时退出");
                future.channel().closeFuture().sync();
                System.out.println("执行了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }).start();

    }


    public void setPort(int port) {
        this.port = port;
    }

    public void setChannelIn(ChannelIn channelIn) {
        this.channelIn = channelIn;
    }

    public void setChannelOut(ChannelOut channelOut) {
        this.channelOut = channelOut;
    }
}
