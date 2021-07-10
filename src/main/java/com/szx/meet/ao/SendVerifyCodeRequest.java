package com.szx.meet.ao;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author szx
 * @Date 2021/7/10 11:57
 * @Description
 */
@Data
public class SendVerifyCodeRequest {
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String phone;
}
