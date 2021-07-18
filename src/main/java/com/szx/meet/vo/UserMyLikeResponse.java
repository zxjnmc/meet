package com.szx.meet.vo;

import lombok.Data;

/**
 * @Author szx
 * @Date 2021/7/18 01:54
 * @Description
 */
@Data
public class UserMyLikeResponse {

    private Integer userId;

    private Integer likeMutully;

    private String description;
}
