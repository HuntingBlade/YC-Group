package com.shytong.config.app;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.shytong.core.auth.SyAuthUtil;
import com.shytong.core.auth.SySecurityManager;
import com.shytong.core.sy.session.SySessionUtil;
import com.shytong.core.util.SyCacheUtil;
import com.shytong.sys.authorization.*;
import com.shytong.sys.sms.service.AliConfig;
import com.shytong.sys.sms.service.ISmsService;
import com.shytong.sys.sms.service.SmsConfig;
import com.shytong.sys.sms.service.SmsFactory;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.config.app
 * @Description:
 * @date 2018-01-0418:10
 */
@Configuration
public class AppConfig {
    @Value("${sy.appmode}")
    private String appmode;
    @Bean(name = "smsService")
    public ISmsService smsService(){
        SmsConfig s=new SmsConfig();
        s.setSendAppId("bigsmall");
        AliConfig aliConfig=new AliConfig();
        aliConfig.setAppkey("24438605");
        aliConfig.setSecret("119bf7c43dbad19adffe00297552ff75");
        aliConfig.setUrl("http://gw.api.taobao.com/router/rest");
        aliConfig.setFreeSignName("奔跑的人生");
        Map<String,String> codes=new HashMap<>();
        codes.put("100001","SMS_71180796");
        aliConfig.setCodes(codes);

      //

        if("dep".equals(appmode)){
            return SmsFactory.getSmsTest(s);
        }
        return SmsFactory.getObject(s,aliConfig);
    }



    @Bean
        public ObjectMapper ObjectMapper(){
            ObjectMapper objectMapper=new ObjectMapper();
           objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
           objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
               @Override
               public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException { arg1.writeString("");
               }
           });
           return objectMapper;
        }

//        @Bean
//        public SysUserRealm sysUserRealm(){
//
//
//
//
//            return new SysUserRealm();
//        }

    @Bean
    public SyAuthUtil syAuthUtil(SysUserRealm sysUserRealm,
                                 SysAuthorizingRealm sysAuthorizingRealm,
                                 UserRealm userRealm,UserAuthorizingRealm userAuthorizingRealm,
                                 SysUserMerchantRealm sysUserMerchantRealm



    ){
        SySecurityManager sySecurityManager=   new SySecurityManager();
        sySecurityManager.setAuthorizingRealm(sysAuthorizingRealm);
        sySecurityManager.setAuthenticingiRealm("login",sysUserRealm);
        sySecurityManager.setAuthenticationInfoDeal(sysUserRealm);
        sySecurityManager.setSysTemCode("SHYTONG");
        SyAuthUtil.DEFAULT_SYSTEM_CODE="SHYTONG";
        SyAuthUtil.sySecurityManagerMap.put("SHYTONG",sySecurityManager);
        //商家
        SySecurityManager merchantManager=   new SySecurityManager();
        merchantManager.setAuthorizingRealm(sysAuthorizingRealm);

        merchantManager.setAuthenticingiRealm("login",sysUserMerchantRealm);
        merchantManager.setAuthenticationInfoDeal(sysUserMerchantRealm);
        merchantManager.setSysTemCode("MERCHANT");
        SyAuthUtil.sySecurityManagerMap.put("MERCHANT",merchantManager);

        //组织者





        SySecurityManager sySecurityManager2=   new SySecurityManager();
        sySecurityManager2.setAuthorizingRealm(userAuthorizingRealm);
        sySecurityManager2.setAuthenticingiRealm("wx",userRealm);
        sySecurityManager2.setAuthenticingiRealm("login",userRealm);
        sySecurityManager2.setAuthenticationInfoDeal(userRealm);
        sySecurityManager2.setSysTemCode("USER");
        SyAuthUtil.sySecurityManagerMap.put("USER",sySecurityManager2);


        return new SyAuthUtil();

    }

    @Bean
    public SySessionUtil sySessionUtil(RedisTemplate redisTemplate){

        SySessionUtil.setRedisTemplate(redisTemplate);
        return new SySessionUtil();
    }



    @Bean
    public SyCacheUtil syCacheUtil(CacheManager cacheManager ){

        SyCacheUtil.cacheManager=cacheManager;


        return new SyCacheUtil();
    }




}
