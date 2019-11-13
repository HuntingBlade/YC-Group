package com.shytong.config.beetl;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeetlBootConfig {
	
    @Value("${tpl.file.path}")
    private String tplPath;
	
    @Value("${tpl.file.resourcetype}")
    private String resourcetype;
	        @Bean(initMethod = "init", name = "beetlConfig")
	        public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
	                BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
	                try {	                			                		
	                		if("class".equals(resourcetype)){	                			
	                			ClasspathResourceLoader resourceLoader1 = new ClasspathResourceLoader("/templates");
	                			beetlGroupUtilConfiguration.setResourceLoader(resourceLoader1);
	                		}else{
	                			String root = tplPath;
		                		FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
	                			beetlGroupUtilConfiguration.setResourceLoader(resourceLoader);	                			
	                		}
	                        return beetlGroupUtilConfiguration;
	                } catch (Exception e) {
	                        throw new RuntimeException(e);
	                }
	        }
	        @Bean(name = "beetlViewResolver")
	        public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
	                BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
	                beetlSpringViewResolver.setSuffix(".html");
	                beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
	                beetlSpringViewResolver.setOrder(0);
	                beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
	                return beetlSpringViewResolver;
	        }

}
