package com.kanzhun;

/**
 * @author:xing.yl
 * @date: 18/10/31
 */
public class MyRunnable implements Runnable {

    public static void main(String[] args) {

        Thread thread = new Thread(new MyRunnable());
        thread.start();
        System.out.println("123");

    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("who are you");
    }
}
