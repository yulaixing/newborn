package com.kanzhun.Main;

import com.kanzhun.Model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:xing.yl
 * @date: 18/12/25
 */
public class Bootstrap {

    public static void main(String[] args) {

        String[] xml={"loadBean-applicationcontext.xml"};

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(xml);


        User user =(User)applicationContext.getBean("user");

        System.out.println(user.getUserGeek().getUserId());




    }

}
