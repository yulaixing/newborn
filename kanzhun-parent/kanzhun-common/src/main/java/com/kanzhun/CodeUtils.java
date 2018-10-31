package com.kanzhun;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author:xing.yl
 * @date: 18/10/24
 */
public class CodeUtils {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String clientMsg="测试ceshi";
        String clientEncode = URLEncoder.encode(clientMsg, "UTF-8");
        //---------------------------

        String tomcatDecode = URLDecoder.decode(clientEncode, "iso-8859-1");

        //----------------------------
        String serverEncode = URLEncoder.encode(tomcatDecode, "iso-8859-1");

        String decode = URLDecoder.decode(serverEncode, "utf-8");

        System.out.println(decode);
        //--------------------------
        byte[] bytes = tomcatDecode.getBytes("iso-8859-1");
        String s = new String(bytes, "utf-8");

        System.out.println(s);



    }

}
