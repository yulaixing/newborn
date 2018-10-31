package com.kanzhun.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author:xing.yl
 * @date: 18/10/27
 */
@Controller
@RequestMapping("/resstatus")
public class ResStatusController {



    @RequestMapping("/500")
    public void fiveHundredError(HttpServletResponse response){

        String str="12a";
        int i = Integer.valueOf(str);
    }








}
