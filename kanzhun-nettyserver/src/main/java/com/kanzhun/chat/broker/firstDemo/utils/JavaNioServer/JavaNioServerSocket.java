package com.kanzhun.chat.broker.firstDemo.utils.JavaNioServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:xing.yl
 * @date: 18/12/4
 */
public class JavaNioServerSocket {

    private static JavaNioServerSocket javaNioServerSocket;

    private ExecutorService connectPool = Executors.newFixedThreadPool(5);

    private final int Max_Size = 2048;

    private Charset charset = Charset.forName("UTF-8");

//    private ByteBuffer byteBuffer=ByteBuffer.allocate(Max_Size);

    private JavaNioServerSocket() {


        start();

    }

    public static JavaNioServerSocket getInstance() {
        synchronized (JavaNioServerSocket.class) {
            if (javaNioServerSocket == null) {
                javaNioServerSocket = new JavaNioServerSocket();
            }
            return javaNioServerSocket;
        }

    }

    private void start() {
        System.out.println("1");
        try {

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open();
            serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888), 108);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start");
            listener(selector);
        } catch (Exception e) {

        } finally {

        }


    }

    private void listener(final Selector selector) {

        try {
            while (true) {
                Thread.sleep(1000);
                int select = selector.select();
                if (select > 0) {
                    connectPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            Set<SelectionKey> selectionKeys = selector.selectedKeys();
                            Iterator<SelectionKey> iterator = selectionKeys.iterator();
                            handler(selector, iterator);


                        }
                    });
                }
            }

        } catch (Exception e) {


        } finally {

            System.out.println("关闭线程池");
            connectPool.shutdown();

        }


    }

    private void handler(final Selector selector, final Iterator<SelectionKey> iterator) {


        while (iterator.hasNext()) {

            SelectionKey key = iterator.next();

            if (!key.isValid()) {
                continue;
            }

            if (key.isAcceptable()) {

                SocketChannel accept = null;
                try {

                    System.out.println("接收事件");

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

                    accept = serverSocketChannel.accept();

                    accept.configureBlocking(false);

                    accept.register(selector, SelectionKey.OP_READ);

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    byteBuffer.put("234".getBytes());
                    byteBuffer.flip();
                    accept.write(byteBuffer);
                    byteBuffer.clear();

                } catch (Exception e) {
                    key.cancel();
                    try {
                        accept.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            if (key.isReadable() && key.isValid()) {
                try {
                    System.out.println("读取事件");
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(Max_Size);
                    int read = 0;
                    byteBuffer.clear();
                    StringBuilder sb = new StringBuilder();
                    while ((read = sc.read(byteBuffer)) > 0) {

                        byteBuffer.flip();
                        byte[] temp = new byte[read];
                        byteBuffer.get(temp, 0, read);
                        sb.append(new String(temp));
                        byteBuffer.clear();
                    }

                    /*30秒后停止*/
                    if (read == -1) {
                        closeChannel(key, sc);
                    }
                    System.out.println(sb.toString());

                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    System.out.println("位置"+bb.position());
                    bb.put("收到".getBytes());
                    bb.flip();
                    sc.write(bb);
                    bb.clear();

                } catch (Exception e) {
                    key.cancel();
                }
            }


        }

        iterator.remove();

    }

    private void returnMsg(final SocketChannel socketChannel) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                ByteBuffer buf = ByteBuffer.allocate(Max_Size);
                buf.put(new String("receive msg").getBytes());
                buf.flip();
                try {
                    socketChannel.write(buf);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }

    private void closeChannel(SelectionKey key, SocketChannel socketChannel) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private static String getString(ByteBuffer buffer) {
        String string = "";
        try {
            for (int i = 0; i < buffer.position(); i++) {
                string += (char) buffer.get(i);
            }
            return string;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }


    public static void main(String[] args) {

    }


}
