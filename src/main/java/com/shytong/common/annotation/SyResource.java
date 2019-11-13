package com.shytong.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sytong
 * @Package com.shytong.common.annotation
 * @Description:
 * @date 2018-04-0410:03
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE} )
public  @interface SyResource {

    String[] value() default "";
    String system() default "";
}
