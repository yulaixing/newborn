package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:xing.yl
 * @date: 18/11/27
 */
public class ReentranLockExample implements Runnable{


    public static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static int i=0;


    public void reentranLockss(){

    }

    public static void main(String[] args) {
        ReentranLockExample example = new ReentranLockExample();



        new Thread(example,"thread1----").start();
        new Thread(new MyRunnable(),"thread2----").start();

    }

    @Override
    public void run() {

        for(int j=0;j<10;j++){

            try {
                reentrantLock.lock();
                i++;
                System.out.println(Thread.currentThread().getName()+i);
            }finally {
                System.out.println("haha");
                reentrantLock.unlock();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(i);



    }
}

 class MyRunnable implements Runnable{


     @Override
     public void run() {
         for(int j=0;j<10;j++){

             try {
                 ReentranLockExample.reentrantLock.lock();
                 ReentranLockExample.i++;
                 System.out.println(Thread.currentThread().getName()+ReentranLockExample.i++);
             }finally {
                 System.out.println("haha");
                 ReentranLockExample.reentrantLock.unlock();

                 try {
                     Thread.sleep(3000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }

     }
 }
