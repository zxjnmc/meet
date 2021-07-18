package com.szx.meet.vo;

import lombok.Data;

/**
 * @Author szx
 * @Date 2021/7/17 21:00
 * @Description
 */
@Data
public class UserLikeMeResponse {

    private Integer likeMutully;

    private Integer userId;

    private String description;
}
