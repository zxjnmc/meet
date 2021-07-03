package com.szx.meet.controller;

import com.szx.meet.annotation.NotEmpty;
import com.szx.meet.ao.UserRequest;
import com.szx.meet.response.Result;
import com.szx.meet.service.OauthService;
import com.szx.meet.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author szx
 * @Date 2021/3/28 01:19
 * @Description 授权控制器
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private OauthService oauthService;

    @PostMapping("/login")
    @NotEmpty({"name", "password"})
    public Result<UserVO> login(@RequestBody UserRequest userRequest) {
        return Result.success(oauthService.login(userRequest));
    }

}
