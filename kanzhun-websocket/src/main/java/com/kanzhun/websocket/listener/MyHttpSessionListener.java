package com.kanzhun.websocket.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author:xing.yl
 * @date: 18/11/4
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    private Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);



    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("我擦 我创建了么s");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
