package com.szx.meet.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author szx
 * @Date 2021/3/28 01:25
 * @Description 用户请求类
 */
@Data
public class UserRequest implements Serializable {

    /**
     * 用户名称
     */
    private String name;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 登录渠道 1-web端 2-小程序
     */
    private Integer channelType;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String verifyCode;

}