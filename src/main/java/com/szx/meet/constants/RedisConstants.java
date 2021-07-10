package com.szx.meet.constants;

/**
 * @Author szx
 * @Date 2021/7/9 23:59
 * @Description
 */
public interface RedisConstants {

    String LOGIN_INFO = "meetLoginInfo:%s";

    String ACCESS_TOKEN = "meetAccessToken:%s";

    Long ACCESS_TOKEN_TTL = 30 * 3600 * 24L;

    String RETRIEVE_PASSWORD_INFO = "retrievePassword:%s";

    Long RETRIEVE_PASSWORD_TTL = 30 * 60L;
}
