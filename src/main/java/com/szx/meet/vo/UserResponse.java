package com.szx.meet.vo;

import lombok.Data;

/**
 * @Author szx
 * @Date 2021/7/4 11:28
 * @Description
 */
@Data
public class UserResponse {

    private Integer userId;

    private Integer channelType;

    private String accessToken;

    private Integer isNew;

    private String phone;
}
