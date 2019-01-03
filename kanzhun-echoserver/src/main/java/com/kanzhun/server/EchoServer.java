package com.kanzhun.server;

import com.kanzhun.deencoder.MessageDecoder;
import com.kanzhun.deencoder.MessageEncoder;
import com.kanzhun.server.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;

/**
 * @author:xing.yl
 * @date: 18/12/30
 */
public class EchoServer {


//    static final boolean SSL = System.getProperty("ssl") != null;

    static final int PORT = 12306;


    public static void main(String[] args) {

        System.out.println("EchoServer.main start");

        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;


        final SslContext sslCtx;
        try {

//            if (SSL) {
//                SelfSignedCertificate ssc = new SelfSignedCertificate();
//                sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
//            } else {
//                sslCtx = null;
//            }

            /*boss,worker*/
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline p = ch.pipeline();
//                            if (sslCtx != null) {
//                                p.addLast(sslCtx.newHandler(ch.alloc()));
//                                System.out.println("需要ssl认证");
//                            }
                            p.addLast(new MessageDecoder());

                            p.addLast(new MessageEncoder());

                            p.addLast(new EchoServerHandler());


                        }
                    });

            ChannelFuture f = b.bind(PORT).sync();

            System.out.println("serverBootstrap 配置启动完成");

            f.channel().closeFuture().sync();

            System.out.println(" EchoServer.main end");

        } catch (Exception e) {

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

}


