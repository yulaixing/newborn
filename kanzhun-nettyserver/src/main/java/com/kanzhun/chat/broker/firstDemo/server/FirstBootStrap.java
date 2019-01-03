package com.kanzhun.chat.broker.firstDemo.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:xing.yl
 * @date: 18/11/10
 */
public class FirstBootStrap {

    private static volatile boolean running = true;

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application_firstdemo.xml");

        applicationContext.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                applicationContext.stop();
            } catch (Throwable t) {
            } finally {
            }
            synchronized (FirstBootStrap.class) {
                running = false;
            }
        }));

        synchronized (FirstBootStrap.class) {
            while (running) {
                try {
                    FirstBootStrap.class.wait();
                } catch (Throwable e) {
                }
            }
        }



    }

}
