package com.szx.meet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author szx
 * @Date 2021/3/27 20:45
 * @Description
 */

@Order(3)
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping))")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping))")
    public void getMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping))")
    public void deleteMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping))")
    public void putMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping))")
    public void requestMapping() {
    }

    @Pointcut("postMapping() || getMapping() || deleteMapping() || putMapping() || requestMapping()")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 校验token
        AccessTokenVerify.verify(joinPoint);
        // 校验请求参数
//        ParameterVerify.verify(joinPoint);
    }

//    @AfterReturning(value = "pointcut()", returning = "obj")
//    public void afterReturning(JoinPoint joinPoint, Object obj) throws NoSuchFieldException, SecurityException,
//            IllegalArgumentException, IllegalAccessException {
//        ReturnHandler.handle(joinPoint, obj);
//    }
}
