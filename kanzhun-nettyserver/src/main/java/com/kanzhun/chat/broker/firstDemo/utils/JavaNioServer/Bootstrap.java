package com.kanzhun.chat.broker.firstDemo.utils.JavaNioServer;

/**
 * @author:xing.yl
 * @date: 18/12/4
 */
public class Bootstrap {

    private static volatile boolean running = true;


    public static void main(String[] args) {

        final long startTime = System.currentTimeMillis();

        JavaNioServerSocket.getInstance();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {

                final long endTime=System.currentTimeMillis();
                System.out.println("运行时间"+(endTime-startTime));

                synchronized (Bootstrap.class){
                    running=false;
                    Bootstrap.class.notify();
                    System.out.println("唤醒自己");
                }


            }
        });

        synchronized (Bootstrap.class){

            while (running){
                try {
                    Bootstrap.class.wait();
                    System.out.println("解锁了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }


    }

}
