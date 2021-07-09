package com.szx.meet.aspect;

import com.alibaba.fastjson.JSONObject;
import com.szx.meet.annotation.AccessToken;
import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.exception.BizException;
import com.szx.meet.thread.ThreadLocalRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author szx
 * @Date 2021/3/27 20:21
 * @Description
 */
public class AccessTokenVerify {
    static void verify(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        boolean tokenRequired = false;

        Class<?> clazz = method.getDeclaringClass();
        AccessToken accessTokenAnnotation;
        // 先获取类注解
        if (clazz.isAnnotationPresent(AccessToken.class)) {
            accessTokenAnnotation = clazz.getAnnotation(AccessToken.class);
            tokenRequired = accessTokenAnnotation.required();
        }
        // 再获取方法注解
        if (method.isAnnotationPresent(AccessToken.class)) {
            accessTokenAnnotation = method.getAnnotation(AccessToken.class);
            tokenRequired = accessTokenAnnotation.required();
        }
        if (!tokenRequired) {
            return;
        }

        String accessToken = request.getHeader("access-token");
        //token为空
        if (StringUtils.isEmpty(accessToken)) {
            throw new BizException(BizErrorCode.ACCESS_TOKEN_ERROR);
        }
        JSONObject user = ThreadLocalRequest.getUser();
        if (user == null) {
            throw new BizException(BizErrorCode.ACCESS_TOKEN_ERROR);
        }
    }
}
