package com.shytong.common.web.login;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.JsonObject;
import com.shytong.common.resultcode.ResultCode;
import com.shytong.modules.mgr.model.MgrDo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 判断用户是否登录，未登录则跳转到登录页面
 * @Author: CL
 * @Date: 2019/12/19 9:56
 */
@Controller
@Component
public class LoginInterceptor implements HandlerInterceptor {

    protected Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        String userId = (String) session.getAttribute("userInfo");
        if (StringUtils.isEmpty(userId)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            response.sendRedirect("/front/public/admin/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
