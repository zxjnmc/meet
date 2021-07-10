package com.szx.meet.service;

import com.szx.meet.ao.ModifyPasswordRequest;
import com.szx.meet.ao.RetrievePasswordRequest;
import com.szx.meet.ao.SendVerifyCodeRequest;
import com.szx.meet.ao.UserRequest;
import com.szx.meet.vo.UserResponse;

import javax.servlet.http.HttpServletRequest;

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
    UserResponse login(UserRequest userRequest, HttpServletRequest request);

    /**
     * 功能描述:退出登录
     *
     * @param
     * @return void
     * @author szx
     * @date 2021/7/9 20:55
     */
    void logout();

    /**
     * 功能描述:修改密码
     *
     * @param modifyPasswordRequest modifyPasswordRequest
     * @return void
     * @author szx
     * @date 2021/7/9 20:57
     */
    void modifyPassword(ModifyPasswordRequest modifyPasswordRequest);

    /**
     * 功能描述:找回密码
     *
     * @param retrievePasswordRequest retrievePasswordRequest
     * @return void
     * @author szx
     * @date 2021/7/9 21:04
     */
    void retrievePassword(RetrievePasswordRequest retrievePasswordRequest);

    /**
     * 功能描述:发送找回密码的验证码
     *
     * @param sendVerifyCodeRequest sendVerifyCodeRequest
     * @return java.lang.String
     * @author szx
     * @date 2021/7/10 12:00
     */
    String sendVerifyCode(SendVerifyCodeRequest sendVerifyCodeRequest);
}
