package com.kanzhun.chat.broker.firstDemo.utils.Net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author:xing.yl
 * @date: 18/12/3
 */
public class INetAddressExample  {



    public static void main(String[] args) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName("www.baidu.com");

            String hostAddress = inetAddress.getHostAddress();
            System.out.println(hostAddress);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }




    }

}
