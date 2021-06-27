package com.szx.meet.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author szx
 * @Date 2021/3/28 01:25
 * @Description 用户请求类
 */
@Data
public class UserAO implements Serializable {

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;
}
