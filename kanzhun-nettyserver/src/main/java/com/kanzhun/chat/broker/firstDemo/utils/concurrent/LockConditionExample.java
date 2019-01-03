package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:xing.yl
 * @date: 18/11/29
 */
public class LockConditionExample implements Runnable{

    ReentrantLock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public static void main(String[] args) {


        Thread thread = new Thread(new LockConditionExample());

        thread.start();


        Scanner scanner = new Scanner(System.in);

    }

    @Override
    public void run() {

        try{
            lock.lock();

//        condition.signal();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("result");
        }finally {
            lock.unlock();
        }


    }
}
