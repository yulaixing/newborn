package com.kanzhun.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:xing.yl
 * @date: 18/11/10
 */
public class TestBeanJunit {

    private static Logger logger = LoggerFactory.getLogger(TestBeanJunit.class);


    public static void main(String[] args) {


        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application_server.xml");

        TestBean testBean = (TestBean) applicationContext.getBean("testBean");

        logger.info("fromNettyServer "+testBean.getId());

        System.out.println(testBean.getId());
    }
}
