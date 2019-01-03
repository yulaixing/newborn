package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author:xing.yl
 * @date: 18/11/30
 */
public class ReadWriteLockExample implements Runnable {

    private static volatile int i = 5;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    public static void main(String[] args) {
        /*多线程竞争共同资源*/
        ReadWriteLockExample readWriteLockExample = new ReadWriteLockExample();

        Thread thread1 = new Thread(readWriteLockExample, "thread-1");
        Thread thread2 = new Thread(readWriteLockExample, "thread-2");

        thread1.start();
        thread2.start();
        Scanner scanner = new Scanner(System.in);


    }

    @Override
    public void run() {
        put();
//        get();

    }

    private void put() {

        try {
            /*写入锁*/
            writeLock.lock();
            i++;
            readLock.lock();
            System.out.println(i);
            readLock.unlock();
        } catch (Exception e)

        {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();

        }
    }

//    private void get() {
//        try {
//            readLock.lock();
//            System.out.println(i);
//        } finally {
//
//            readLock.unlock();
//        }
//    }
}



