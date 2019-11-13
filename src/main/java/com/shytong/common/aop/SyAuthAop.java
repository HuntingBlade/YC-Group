package com.shytong.common.aop;

import com.shytong.common.annotation.SyResource;
import com.shytong.common.exception.ApiBizException;
import com.shytong.core.auth.Subject;
import com.shytong.core.auth.SyAuthUtil;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.util.SyStringUtils;
import com.shytong.sys.authorization.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author sytong
 * @Package com.shytong.common.aop
 * @Description:
 * @date 2018-04-0322:04
 */
@Aspect
@Component
public class SyAuthAop {

    @Pointcut(value = "@annotation(com.shytong.common.annotation.SyResource)")
    public void syResource(){

    }

    @Around("syResource()")
    public Object  around(ProceedingJoinPoint pjp) throws Throwable {
        Object o=null;
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        SyResource login = method.getAnnotation(SyResource.class);
       Object [] args=pjp.getArgs();
       Class [] parameterTypes=   ((MethodSignature) pjp.getSignature()).getParameterTypes();
        if(login!=null) {

            Subject s = SyAuthUtil.getSubject(login.system());
            if (s == null || !s.isAuthented()) {
               throw new ApiBizException(SyConstant.ERR_AUTH_NO_LOGIN, "请先登录");
            }

            System.out.println(login.value().length+"%%%%%%%%%%%%");

//            Object u=s.getAuthenticationInfo().getUserInfo();


            if (login.value().length > 0 && !SyStringUtils.isBlank(login.value()[0])) {

              if(!s.hasAllResources(Arrays.asList(login.value()))){

//                  throw new ApiBizException(SyConstant.ERR_AUTH_RESOURCE_FAIL, "无权限");


              } ;

                //权限验证
            }
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i].equals(User.class)  ) {



                    args[i] = s.getAuthenticationInfo().getUserInfo();

                    break;
                }
            }
        }


       long s=System.currentTimeMillis();
       o =pjp.proceed(args);
        System.out.println("执行时间："+(System.currentTimeMillis()-s));
        System.out.println("执行时间2："+System.currentTimeMillis());
        return o;
    }




}
