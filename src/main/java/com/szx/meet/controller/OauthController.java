package com.szx.meet.controller;

import com.szx.meet.ao.UserRequest;
import com.szx.meet.vo.UserResponse;
import com.szx.meet.response.ApiResult;
import com.szx.meet.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author szx
 * @Date 2021/7/4 10:38
 * @Description
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private OauthService oauthService;

    @PostMapping("/login")
    public ApiResult<UserResponse> login(@RequestBody UserRequest userRequest) {
        return ApiResult.success(oauthService.login(userRequest));
    }

}
