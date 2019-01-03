package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:xing.yl
 * @date: 18/11/26
 */
public class ThreadExample {


    private static Object obj= new Object();

    private static class MyRunnable implements Runnable{


        @Override
        public void run() {
            synchronized (obj){
                System.out.println("myrunnable is run");
                try {
//                    this.wait();

                    obj.wait();
//                    Thread.currentThread().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 8, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(8000));
        threadPoolExecutor.allowCoreThreadTimeOut(false);

        Thread thread = new Thread(new MyRunnable());

        thread.start();


    }

}
