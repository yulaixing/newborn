package com.kanzhun.websocket.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * @author:xing.yl
 * @date: 18/11/26
 */
@WebFilter
public class MyFilter implements Filter {

    public static void main(String[] args) {


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        if(httpServletRequest.getMethod().equalsIgnoreCase("get")){


        filterChain.doFilter(new GetHttpServletRequestWrapper(httpServletRequest),servletResponse);
        }else{
            filterChain.doFilter(httpServletRequest,servletResponse);
        }
    }


    /*装饰类，重写了他获取参数的方法*/
    private class GetHttpServletRequestWrapper extends HttpServletRequestWrapper{

        private String chatset="UTF-8";

        public GetHttpServletRequestWrapper(HttpServletRequest request) {

            super(request);
        }

        @Override
        public String getParameter(String name) {
            String value = super.getParameter(name);
            return value=value==null?null:convert(value);
        }

        private String convert(String target){
            return target.trim();
        }

    }



    @Override
    public void destroy() {

    }
}
