package com.shytong.core.auth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0321:08
 */
@WebFilter(filterName="syAuthFilter", urlPatterns ="/*")

public class SyAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        SubjectContext subjectContext=new SubjectContext();
        subjectContext.setRequest(req);
        SyAuthThreadContext.remove();
        SyAuthThreadContext.bind(subjectContext);
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        chain.doFilter( request,  response);
    }

    @Override
    public void destroy() {

    }
}
