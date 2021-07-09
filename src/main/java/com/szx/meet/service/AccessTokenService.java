package com.szx.meet.service;

import com.szx.meet.vo.UserResponse;

/**
 * @Author szx
 * @Date 2021/7/10 00:02
 * @Description
 */
public interface AccessTokenService {

    /**
     * 功能描述:设置accessToken
     *
     * @param loginInfo    loginInfo
     * @param accessToken  accessToken
     * @param userResponse userResponse
     * @author szx
     * @date 2021/7/10 00:03
     */
    void setAccessToken(String loginInfo, String accessToken, UserResponse userResponse);
}
