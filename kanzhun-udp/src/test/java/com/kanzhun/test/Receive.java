package com.kanzhun.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author:xing.yl
 * @date: 18/9/3
 */
public class Receive {

    public static void main(String[] args) throws IOException {
        /*每一个应用只能绑定一个端口*/
        DatagramSocket datagramSocket = new DatagramSocket(9090);
//准备空的数据包用于存放数据。
        byte[] buf = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length); // 1024
        //调用udp的服务接收数据
        datagramSocket.receive(datagramPacket); //receive是一个阻塞型的方法，没有接收到数据包之前会一直等待。 数据实际上就是存储到了byte的自己数组中了。
        System.out.println("接收端接收到的数据：" + new String(buf, 0, datagramPacket.getLength())); // getLength() 获取数据包存储了几个字节。
        System.out.println("receive阻塞了我，哈哈");
        //关闭资源
        datagramSocket.close();
    }

}
