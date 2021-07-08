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
    /**
     *
     * 功能描述:
     * 稽首皈依苏悉地
     * 头面顶礼七俱胝
     * 我今称赞大准提
     * 唯愿慈悲垂加护
     *
     * 南无飒哆喃
     * 三秒三菩陀
     * 俱胝喃，怛胝陀，
     * ong，折戾主戾准提娑婆诃
     *
     * @param null null
     * @return
     * @author szx
     * @date 2021/7/5 01:33
     */

    @Autowired
    private OauthService oauthService;

    @PostMapping("/login")
    public ApiResult<UserResponse> login(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = oauthService.login(userRequest);
        return ApiResult.success(userResponse);
    }


}
