package com.kanzhun;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author:xing.yl
 * @date: 18/10/15
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);



    public static void main(String[] args) {
        logger.info("main method");

        String url = "http://www.biquge5200.com/31_31746/12331189.html";
        Document document = getDoc(url);
        String title = document.title();
        System.out.println(title);
        String text = document.select("#content").text();

        System.out.println(text);
    }

    private static Document getDoc(String url) {

        boolean flag=false;
        Document document=null;

        do{
            try {
                document = Jsoup.connect(url).
                        userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) " +
                                "AppleWebKit/537.31 (KHTML, like Gecko) " +
                                "Chrome/26.0.1410.64 Safari/537.31").
                        timeout(5000).get();


            } catch (IOException e) {

                flag=true;

                e.printStackTrace();
            }


        }while (flag);

        return document;


    }



}
