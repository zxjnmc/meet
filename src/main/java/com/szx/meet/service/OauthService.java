package com.szx.meet.service;

import com.szx.meet.ao.UserRequest;
import com.szx.meet.vo.UserResponse;

/**
 * @Author szx
 * @Date 2021/7/4 11:30
 * @Description
 */
public interface OauthService {

    /**
     * 功能描述:用户登录
     *
     * @param userRequest userRequest
     * @return com.szx.meet.vo.UserResponse
     * @author szx
     * @date 2021/7/4 11:34
     */
    UserResponse login(UserRequest userRequest);
}
