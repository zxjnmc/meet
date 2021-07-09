package com.szx.meet.ao;

import lombok.Data;

/**
 * @Author szx
 * @Date 2021/7/9 20:59
 * @Description
 */
@Data
public class RetrievePasswordRequest {

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 验证码
     */
    private String verifyCode;

}
