package com.shytong.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sytong
 * @Package com.shytong.core.util
 * @Description:
 * @date 2018-01-2914:29
 */
public class SyRequestUtil {
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
           return  null;
        }
        return attrs.getRequest();
    }
}
