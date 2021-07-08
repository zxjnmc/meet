package com.szx.meet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author szx
 * @Date 2021/3/27 20:45
 * @Description
 */

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(* com.szx.meet.controller.*.*(..))")
    public void handle() {
    }

    @Before("handle()")
    public void before(JoinPoint joinPoint) {
        // 校验token
        AccessTokenVerify.verify(joinPoint);
    }
}
