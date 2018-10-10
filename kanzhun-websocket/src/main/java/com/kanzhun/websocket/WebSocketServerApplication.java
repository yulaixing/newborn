package com.kanzhun.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:xing.yl
 * @date: 18/8/27
 */
@SpringBootApplication
@Controller
@Configuration
public class WebSocketServerApplication {

    @RequestMapping("setCookie")
    @ResponseBody
    public String setCookie(HttpServletResponse res){


        Cookie cookie=new Cookie("ticket","123");

        res.addCookie(cookie);


        return "success!";
    }


    @RequestMapping("hello")
    @ResponseBody
    public String hello(@RequestParam( name = "price",defaultValue = "0") double price, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();

        for(Cookie c:cookies){
            System.out.println(c.getName()+"::"+c.getValue());
        }

        return "hello world me！";
    }

    public static void main(String[] args) {
        SpringApplication.run(WebSocketServerApplication.class,args);
    }

}
