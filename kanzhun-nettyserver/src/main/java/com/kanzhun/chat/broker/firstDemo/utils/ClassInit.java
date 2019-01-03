package com.kanzhun.chat.broker.firstDemo.utils;

/**
 * @author:xing.yl
 * @date: 18/11/23
 */
public class ClassInit {



    private static int c=3;

    static {
        System.out.println("ojbk");
    }

    private int a;

    private int b=a+1;

    public ClassInit(){
        //构造方法之前已经初始化实例变量了
        System.out.println(a);
        System.out.println(b);
    }


    public static void main(String[] args) {

        StringBuilder sb=new StringBuilder();
        sb.append("123");

        System.out.println(ClassInit.c);

        //构造函数 创建实例对象。分配内存空间，实例变量初始化，将引用变量指向内存地址
        ClassInit classInit = new ClassInit();
//        System.out.println(classInit.b);



    }

}
