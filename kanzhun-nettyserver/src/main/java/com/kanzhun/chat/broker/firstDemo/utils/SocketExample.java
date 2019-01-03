package com.kanzhun.chat.broker.firstDemo.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author:xing.yl
 * @date: 18/12/3
 */
public class SocketExample {

    public static void main(String[] args) throws Exception {

        final Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        try {

            InputStream inputStream = socket.getInputStream();

            int read = 0;
            while (read == 0) {
                read = inputStream.available();
            }
            byte[] bytes = new byte[read];
            inputStream.read(bytes);

            System.out.println(new String(bytes,"UTF-8"));



            OutputStream outputStream = null;
            try {
                outputStream = socket.getOutputStream();

                outputStream.write("4".getBytes());
                outputStream.flush();

                TimeUnit.SECONDS.sleep(2);

                outputStream.write("5".getBytes());
                outputStream.flush();

//                outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }



            TimeUnit.SECONDS.sleep(1);

            read = 0;
            while (read == 0) {
                read = inputStream.available();
            }
            byte[] newByte = new byte[read];
            inputStream.read(newByte);

            System.out.println(new String(newByte,"UTF-8"));




        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
