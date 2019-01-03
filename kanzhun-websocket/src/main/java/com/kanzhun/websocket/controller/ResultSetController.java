package com.kanzhun.websocket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kanzhun.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author:xing.yl
 * @date: 18/10/30
 */
@Controller
@RequestMapping("/resultset")
public class ResultSetController {

    private Logger logger = LoggerFactory.getLogger(ResultSetController.class);


    @RequestMapping("/test")
    public void test(HttpServletResponse response,HttpServletRequest request,
                     @RequestParam(value = "id") String id) {
        try {


            Object servlet = request.getSession().getServletContext().getAttribute("servlet");
            logger.info("servletcontext param is="+servlet);
            //application/json;charset==utf-8
            logger.info(request.getCharacterEncoding());
            logger.info(id);
            ResultSet resultSet = getResultSet();
            //response.setHeader("Content-type","text/html;charset=UTF-8");
            //response.setCharacterEncoding("UTF-8");
            //response.setContentType("application/json;charset=utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSONObject.toJSONString(resultSet));
        } catch (Exception e) {

            logger.error("test is error", e);
        }


    }

    @RequestMapping("/test2")
    public @ResponseBody
    ResultSet
    test2(HttpServletResponse response,
          @RequestParam(value = "id") String id) {
        ResultSet resultSet = null;
        try {
            logger.info(id);
            resultSet = getResultSet();
        } catch (Exception e) {
            logger.error("test2 is error", e);
        }

        return resultSet;


    }

    @RequestMapping("/test3")
    public void

    test3(HttpServletResponse response,
          @RequestParam(value = "id") String id) {
        ResultSet resultSet = null;
        try {
            logger.info(id);
            resultSet = getResultSet();
            response.getWriter().write(JSONObject.toJSONString(resultSet));
        } catch (Exception e) {
            logger.error("test2 is error", e);
        }



    }




    private ResultSet getResultSet() {
        ResultSet resultSet = new ResultSet();
        resultSet.setRescode(0);
        resultSet.setResmsg("成功");
        JSONArray ja = new JSONArray();
        ja.add("a");
        ja.add("b");
        resultSet.setContent(ja.toArray());
        return resultSet;
    }


    public static void main(String[] args) {
        ResultSet resultSet = new ResultSet();
        resultSet.setRescode(0);
        resultSet.setResmsg("成功");
        JSONArray ja = new JSONArray();
        ja.add("a");
        ja.add("b");
        resultSet.setContent(ja.toArray());

        /*对象转为字符串*/
        String s = JSONObject.toJSONString(resultSet);
        System.out.println(s);
        /*json字符串转为jsonObject 对象*/
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject.get("resmsg"));
        /*json字符串转为object对象*/
        ResultSet resultSet1 = JSONObject.parseObject(s, ResultSet.class);
        System.out.println(resultSet1.getRescode());


    }


}
