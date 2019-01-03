package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

/**
 * @author:xing.yl
 * @date: 18/11/25
 */
public class ByteToString {

    public static void main(String[] args) {


        byte[] b=new byte[32];
        StringBuilder sb=new StringBuilder();

        int num=17;
        String str="123";
        for(int i=31;i>=0;i--){
            int a = 0x01 & num;
            num>>=1;
            sb.append(a+"");
        }

        System.out.println(sb.reverse());

    }

}
