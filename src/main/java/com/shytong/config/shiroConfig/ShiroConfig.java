package com.shytong.config.shiroConfig;


/**
 * @author sytong
 * @Package com.shytong.config.shiroConfig
 * @Description:
 * @date 2018-04-0116:54
 */
//@Configuration
public class ShiroConfig {
//    @Bean(name="shiroFilter")
//    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
//        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
//        bean.setSecurityManager(manager);
//        //配置登录的url和登录成功的url
//        bean.setLoginUrl("/login");
//        bean.setSuccessUrl("/home");
//        //配置访问权限
//        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/jsp/login.jsp*", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/loginUser", "anon");
//        filterChainDefinitionMap.put("/logout*","anon");
//        filterChainDefinitionMap.put("/jsp/error.jsp*","anon");
//        filterChainDefinitionMap.put("/jsp/index.jsp*","authc");
//        filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
//        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
//        filterChainDefinitionMap.put("/*.*", "authc");
//        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return bean;
//    }
//    //配置核心安全事务管理器
//    @Bean(name="securityManager")
//    public SecurityManager securityManager() {
//        System.err.println("--------------shiro已经加载----------------");
//        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
//
//        return manager;
//    }
////    //配置自定义的权限登录器
////    @Bean(name="authRealm")
////    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
////        AuthRealm authRealm=new AuthRealm();
////        authRealm.setCredentialsMatcher(matcher);
////        return authRealm;
////    }
}
