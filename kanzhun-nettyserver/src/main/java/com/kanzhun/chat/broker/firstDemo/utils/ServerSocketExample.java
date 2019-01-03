package com.kanzhun.chat.broker.firstDemo.utils;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:xing.yl
 * @date: 18/11/18
 */
public class ServerSocketExample {


    final static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

    private static boolean running = false;

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket accept = serverSocket.accept();
                System.out.println("连接进入");
                running = true;
//                while (running) {
//                    InputStream inputStream = accept.getInputStream();
//                    //网络传输中，长度是不确定的
//                    int count = 0;
//                    while (count == 0) {
//                        count = inputStream.available();
//                        try {
//                            accept.sendUrgentData(0xFF);
//                        } catch (Exception ex) {
//                            running = false;
//                            break;
//                        }
//
//                    }
//                    byte[] b = new byte[count];
//                    inputStream.read(b);
//                    System.out.println(new String(b, "UTF-8"));
//                }

                OutputStream outputStream = accept.getOutputStream();

                TimeUnit.SECONDS.sleep(2);

                outputStream.write("server".getBytes("UTF-8"));

//                outputStream.flush();



            }
        } catch (Exception e) {

        }


    }

}
