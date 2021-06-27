package com.szx.meet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author szx
 * @Date 2021/3/27 20:49
 * @Description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {
    /**
     * 参数
     */
    String[] value() default "";

    /**
     * 参数最大长度,对应value字段的顺序，不填默认不限制
     */
    int[] maxLen() default {};
}
