package com.kanzhun.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:xing.yl
 * @date: 18/8/27
 */
@SpringBootApplication
@Controller
@Configuration
public class WebSocketServerApplication {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello world me！";
    }

    public static void main(String[] args) {
        SpringApplication.run(WebSocketServerApplication.class,args);
    }

}
