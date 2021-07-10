package com.szx.meet.controller;

import com.szx.meet.annotation.AccessToken;
import com.szx.meet.ao.ModifyPasswordRequest;
import com.szx.meet.ao.RetrievePasswordRequest;
import com.szx.meet.ao.UserRequest;
import com.szx.meet.ao.SendVerifyCodeRequest;
import com.szx.meet.vo.UserResponse;
import com.szx.meet.response.ApiResult;
import com.szx.meet.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @AccessToken(required = false)
    public ApiResult<UserResponse> login(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        return ApiResult.success(oauthService.login(userRequest, request));
    }

    @PostMapping("/logout")
    public ApiResult<Void> logout() {
        oauthService.logout();
        return ApiResult.success();
    }

    @PostMapping("/modify-password")
    public ApiResult<Void> modifyPassword(@RequestBody ModifyPasswordRequest modifyPasswordRequest) {
        oauthService.modifyPassword(modifyPasswordRequest);
        return ApiResult.success();
    }

    @PostMapping("/retrieve-password")
    @AccessToken(required = false)
    public ApiResult<Void> retrievePassword(@RequestBody RetrievePasswordRequest retrievePasswordRequest) {
        oauthService.retrievePassword(retrievePasswordRequest);
        return ApiResult.success();
    }

    @GetMapping("/send-verify-code")
    @AccessToken(required = false)
    public ApiResult<String> sendVerifyCode(SendVerifyCodeRequest sendVerifyCodeRequest) {
        return ApiResult.success(oauthService.sendVerifyCode(sendVerifyCodeRequest));
    }

}
