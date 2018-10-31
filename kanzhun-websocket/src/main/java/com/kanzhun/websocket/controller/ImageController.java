package com.kanzhun.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author:xing.yl
 * @date: 18/10/26
 */

@Controller
@RequestMapping("/image")
public class ImageController {
    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @RequestMapping("get")
    public void get(HttpServletResponse response, @RequestParam(value = "imgurl") String imgurl) {


        response.setContentType("image/jpeg");
        byte[] result = null;
        InputStream inputStream = null;
        try {

            URL url = new URL(imgurl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestProperty("Accept-Encoding", "identity");
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            int count = urlConnection.getContentLength();

            //1bt==1字节

            System.out.println(count/1024+"."+count%1024);
            result = new byte[count];

            int readCount = 0;
            while (readCount < count) {
                readCount += inputStream.read(result, readCount, count - readCount);
            }

            OutputStream outputStream = response.getOutputStream();

            outputStream.write(result);

            outputStream.flush();

            outputStream.close();


        } catch (Exception e) {
            logger.error("image get is error", e);

        } finally {
            try {

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {

                logger.error("sdfsdf",e);
            }


        }


    }


}
