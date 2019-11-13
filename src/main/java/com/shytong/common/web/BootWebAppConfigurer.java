package com.shytong.common.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author sytong
 * @Package com.shytong.common.web
 * @Description:
 * @date 2018-04-1922:33
 */
@Configuration
public class BootWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BootInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}