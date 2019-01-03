package com.kanzhun.Main;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * @author:xing.yl
 * @date: 18/12/26
 */
public class DOM4JTest {

    public static void main(String[] args) {

        try {

            URL url = DOM4JTest.class.getClassLoader().getResource("user.xml");
            SAXReader saxReader = new SAXReader();
            Document document ;
            document = saxReader.read(url);


            Element rootElement = document.getRootElement();

            Iterator iterator = rootElement.elementIterator();

            while(iterator.hasNext()){
                Element next = (Element) iterator.next();
                System.out.println(next.getName());
            }
            System.out.println(rootElement.getName());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
