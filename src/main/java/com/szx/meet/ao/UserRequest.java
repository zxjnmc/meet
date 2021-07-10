package com.szx.meet.ao;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author szx
 * @Date 2021/3/28 01:25
 * @Description 用户请求类
 */
@Data
public class UserRequest implements Serializable {

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
     * 登录渠道 1-web端 2-小程序
     */
    @NotNull(message = "登录渠道不能为空")
    private Integer channelType;

    /**
     * 用户密码
     */
    @NotNull(message = "登录密码不能为空")
    private String password;

}