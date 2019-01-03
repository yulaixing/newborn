package com.kanzhun.websocket.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author:xing.yl
 * @date: 18/11/4
 */

@WebListener
public class MyRequestContextListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(MyRequestContextListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        logger.info("MyRequestContextListener is start");

        servletContextEvent.getServletContext().setAttribute("servlet","牛逼");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        logger.info("servlet destroy");

    }
}
