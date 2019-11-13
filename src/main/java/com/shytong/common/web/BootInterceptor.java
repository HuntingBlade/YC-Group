package com.shytong.common.web;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author sytong
 * @Package com.shytong.common.web
 * @Description:
 * @date 2018-04-1922:34
 */
public class BootInterceptor implements HandlerInterceptor {
    public final AtomicLong _count = new AtomicLong();// 计数器

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) {
        long begin_nao_time = System.currentTimeMillis();

        System.out.println("开始时间："+System.currentTimeMillis());
        req.setAttribute("begin_nao_time", begin_nao_time);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object arg2, Exception arg3)
            throws Exception {
        long begin_nao_time = (Long) req.getAttribute("begin_nao_time");
        String real_ip = (String) req.getAttribute("p_real_ip");
        long interval = System.currentTimeMillis() - begin_nao_time;
        System.out.println("结束时间："+System.currentTimeMillis());
       System.out.println("响应时间："+interval);

    }

}