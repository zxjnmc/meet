package com.szx.meet.vo;

import lombok.Data;
import org.apache.ibatis.javassist.runtime.Inner;

/**
 * @Author szx
 * @Date 2021/7/4 11:28
 * @Description
 */
@Data
public class UserResponse {

    private Integer userId;

    private Integer channel;

    private String accessToken;

    private Integer isNew;
}
