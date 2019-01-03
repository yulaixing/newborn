package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

/**
 * @author:xing.yl
 * @date: 18/11/25
 */
public class ThreadLocalExample {

   static ThreadLocal<String> localString = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                localString.set("123");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(localString.get());


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {


                localString.set("456");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(localString.get());

            }
        }).start();




    }

}
