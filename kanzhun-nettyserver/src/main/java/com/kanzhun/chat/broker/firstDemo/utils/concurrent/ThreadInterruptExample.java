package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author:xing.yl
 * @date: 18/11/28
 */
public class ThreadInterruptExample implements Runnable{




    public static void main(String[] args) {


        Thread thread = new Thread(new ThreadInterruptExample());
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();


    }

    @Override
    public void run() {

        for(;;){

            System.out.println("haha");
            try {

                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
                boolean interrupted = Thread.currentThread().isInterrupted();
                System.out.println("interrupted is "+interrupted);
                break;
            }
        }


    }
}
