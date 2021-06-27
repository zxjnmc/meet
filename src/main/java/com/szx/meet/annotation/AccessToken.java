package com.szx.meet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author szx
 * @Date 2021/3/27 20:19
 * @Description token校验
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessToken {

    //是否需要token  true需要校验，false不需要校验  默认true
    boolean required() default true;

}
