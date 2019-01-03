package com.kanzhun;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author:xing.yl
 * @date: 18/10/31
 */
public class MyCallable implements Callable<Integer> {

    public static void main(String[] args) {




        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCallable());


        new Thread(futureTask).start();

        Integer i = null;
        try {
            i = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(i);



    }

    @Override
    public Integer call() throws Exception {

        System.out.println("call");

        return 1;
    }
}
