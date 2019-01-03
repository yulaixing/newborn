package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:xing.yl
 * @date: 18/11/28
 */
public class SynchronizedExample {

    private byte[] b = new byte[0];

    private byte[] c = new byte[1];


    private class MyRunnbale implements Runnable {

        @Override
        public void run() {
            synchronized (b) {

                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                    try {
                        b.notify();



                        b.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }


        }
    }


    private class MyRunnbale2 implements Runnable {
        @Override
        public void run() {
            synchronized (b) {

                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (c) {
                    }
                    System.out.println(Thread.currentThread().getName() + i);

                }


            }

        }

    }

    private class MyRunnbale3 implements Runnable {
        @Override
        public void run() {
            synchronized (c) {

                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (b) {
                    }


                }


            }

        }

    }


    public static void main(String[] args) {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
//        MyRunnbale myRunnbale1 = synchronizedExample.new MyRunnbale();
//        MyRunnbale myRunnbale2 = synchronizedExample.new MyRunnbale();
        MyRunnbale2 myRunnbale2 = synchronizedExample.new MyRunnbale2();
        MyRunnbale3 myRunnbale3 = synchronizedExample.new MyRunnbale3();


        ReentrantLock reentrantLock = new ReentrantLock();

reentrantLock.lock();

        /*死锁现象 jvm处理同步*/
        Thread thread1 = new Thread(myRunnbale2, "线程1");
        thread1.start();

        thread1.yield();
        new Thread(myRunnbale3,"线程2").start();


    }

}
