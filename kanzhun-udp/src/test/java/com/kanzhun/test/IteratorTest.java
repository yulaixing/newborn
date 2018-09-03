package com.kanzhun.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author:xing.yl
 * @date: 18/9/3
 */
public class IteratorTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("a")) {
                iterator.remove();
                continue;
            }

            /*两次iterator会报java.lang.IllegalStateException 错误*/
            //iterator.remove();
            System.out.println(next);

        }


    }

}
