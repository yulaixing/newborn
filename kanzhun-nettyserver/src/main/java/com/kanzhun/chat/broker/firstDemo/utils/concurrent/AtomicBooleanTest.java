package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author:xing.yl
 * @date: 18/11/25
 * 同一段代码 同时只有一段执行
 */
public class AtomicBooleanTest {

    private static AtomicBoolean ab = new AtomicBoolean(false);


    private static class MyRunnable implements Runnable{


        @Override
        public void run() {

            if(ab.compareAndSet(false,true)){


                System.out.println("hahaha");
//                ab.set(false);
            }else{
                System.out.println("oooo");
            }



        }
    }

    public static void main(String[] args) {


        for(int i=0;i<10;i++){

            new Thread(new MyRunnable()).start();
        }





    }

}
