package com.kanzhun.chat.broker.firstDemo.utils.base;

/**
 * @author:xing.yl
 * @date: 18/12/3
 */
public class Sington {


    private Sington() {

    }


    private static class SingtonInner {

        private static Sington sington = new Sington();
        static{
            System.out.println("jiazaile1");
        }

    }


    public static Sington getInstance() {
        return SingtonInner.sington;
    }

    public static void main(String[] args) {
//        Sington.getInstance();

    }

}
