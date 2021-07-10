package com.szx.meet.ao;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String verifyCode;

    /**
     * 新密码
     */
    @NotNull(message = "新密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度必须是6-18位")
    private String newPassword;

}
