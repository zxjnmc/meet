package com.szx.meet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.szx.meet.constants.RedisConstants;
import com.szx.meet.service.AccessTokenService;
import com.szx.meet.util.RedisUtils;
import com.szx.meet.vo.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author szx
 * @Date 2021/7/10 00:03
 * @Description
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void setAccessToken(String loginInfo, String accessToken, UserResponse userResponse) {
        redisUtils.setValueExpire(loginInfo, accessToken, RedisConstants.ACCESS_TOKEN_TTL, TimeUnit.SECONDS);
        redisUtils.setValueExpire(accessToken, JSONObject.toJSON(userResponse), RedisConstants.ACCESS_TOKEN_TTL, TimeUnit.SECONDS);
    }
}
