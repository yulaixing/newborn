package com.kanzhun.chat.broker.firstDemo.utils;

import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author:xing.yl
 * @date: 18/11/18
 */
public class GoogleCache {


    private static class MyRemovalListener implements RemovalListener<Integer, Integer> {

        @Override
        public void onRemoval(RemovalNotification<Integer, Integer> notification) {
            if(notification.getCause()==RemovalCause.EXPIRED){ //主動清除的不管，對要過期的key,清除

                //过期了？

                System.out.println("过期");
            }

//            System.out.println("亚美爹");
        }
    }

    public static void main(String[] args) {

//        final Cache<Integer, Integer> build = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).removalListener(new MyRemovalListener()).build();
//
//        build.put(1, 1);
//        build.put(2, 2);
//
//
//        /**清除过期的key*/
//        build.cleanUp();
//
//        Integer ifPresent = build.getIfPresent(1);
////        System.out.println(ifPresent);
//
////        build.invalidate(1);
//
//        Object o = new Object();
//        synchronized (o) {
//
//            try {
//                o.wait(6000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//        build.cleanUp();
//         ifPresent = build.getIfPresent(1);
//        System.out.println(ifPresent);
//

        try {
            Socket socket1 = new Socket("127.0.0.1", 8888);

            OutputStream outputStream = socket1.getOutputStream();
//            outputStream.close(); socket不要轻易关闭



            outputStream = socket1.getOutputStream();
            outputStream.write("123".getBytes());
            outputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
