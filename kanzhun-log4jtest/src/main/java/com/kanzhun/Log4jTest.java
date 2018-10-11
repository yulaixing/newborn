package com.kanzhun;

import org.apache.log4j.Logger;

/**
 * @author:xing.yl
 * @date: 18/10/11
 */
public class Log4jTest {

    public static void main(String[] args) {

        Logger info = Logger.getLogger("info");
        info.info("log4jtest aa aaa");

    }


}
