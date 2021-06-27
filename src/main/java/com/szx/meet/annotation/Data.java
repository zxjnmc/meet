package com.szx.meet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author szx
 * @Date 2021/3/27 20:58
 * @Description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Data {
    /**
     * 返回只包含
     */
    String[] include() default {};

    /**
     * 返回不包含
     */
    String[] exclude() default {};
}