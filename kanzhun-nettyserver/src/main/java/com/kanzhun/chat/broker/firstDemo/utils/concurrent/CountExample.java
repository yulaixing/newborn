package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author:xing.yl
 * @date: 18/11/22
 */
public class CountExample {

    private static int concurrentCount = 200;
    private static int threadCount = 5000;
    private static volatile Integer i = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static LongAdder longAdder=new LongAdder();

    private static final Object obj = new Object();
    private static SynchronousQueue<Integer> queue = new SynchronousQueue<>();



    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer take = queue.take();
                    System.out.println(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();


        final ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);


        Semaphore semaphore = new Semaphore(concurrentCount);

        for (int i = 1; i <= 5000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        atomicInteger.getAndIncrement();
                        longAdder.increment();
                    } catch (Exception e) {

                        System.out.println("ojbk了？");

                    } finally {
                        semaphore.release();
                        countDownLatch.countDown();
                    }
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(i);
        System.out.println(atomicInteger.get());
        System.out.println(longAdder.intValue());


    }

    private static void add() {

        for (; ; ) {
            int current = i;
            synchronized (obj) {

                int increment = current + 1;
                //多个线程都排在着了
                if (i+1==increment) {
                    //
                    synchronized (obj) {
                        i=increment;
                        return;
                    }

                }

            }
        }


//        i++;
    }


}
