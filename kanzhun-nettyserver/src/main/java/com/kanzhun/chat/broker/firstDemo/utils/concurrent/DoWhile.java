package com.kanzhun.chat.broker.firstDemo.utils.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:xing.yl
 * @date: 18/11/22
 */
public class DoWhile {

    volatile int i = 0;

    AtomicInteger atomic = new AtomicInteger();

    public static void main(String[] args) {
//        int i=1;
//
//        do{
//
//            System.out.println(i++);
//
//        }while(i<=5);
//        try {
//            System.out.println(1 / 0);
//        } catch (Exception e) {
//            System.out.println("水电费水电费");
//        } finally {
//
//            System.out.println("finally");
//        }


        int math = getMath();
        System.out.println(math);


    }

    private static int getMath() {
        int i=5;
        try {
//            System.out.println(1 / 0);
            return i++;
        } catch (Exception e) {
            System.out.println("水电费水电费");
//            return i;
        } finally {
            System.out.println("finally");
           return  ++i;
//            return 2;
        }

//        System.out.println("什么时候执行");
//
//        return i;


    }

}
