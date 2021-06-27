package com.szx.meet.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author szx
 * @Date 2021/3/28 01:25
 * @Description 用户展示类
 */
@Data
public class UserVO implements Serializable {

    private Long id;

    private String name;

    private String accessToken;

    private String email;

    private String phoneNum;

}
