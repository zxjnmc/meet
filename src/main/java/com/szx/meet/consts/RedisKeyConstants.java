package com.szx.meet.consts;

/**
 * @Author szx
 * @Date 2021/3/28 01:36
 * @Description redis常量
 */
public interface RedisKeyConstants {

    /**
     * redis 用户的accessToken
     */
    String ACCESS_TOKEN = "accessToken:%s";

    /**
     * 登录的key
     */
    String LOGIN_KEY = "login:%s";

    /**
     * 登录过期时间
     */
    Long LOGIN_EXPIRE_TIME = 30 * 24 * 60 * 60 * 1000L;
}
