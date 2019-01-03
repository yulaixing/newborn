package com.kanzhun;

/**
 * @author:xing.yl
 * @date: 18/10/31
 */
public class MyThread extends Thread {


    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("abcdef");
    }

    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        myThread.start();
        System.out.println("gogogo");

    }

}
