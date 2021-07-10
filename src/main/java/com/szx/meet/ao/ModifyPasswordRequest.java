package com.szx.meet.ao;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author szx
 * @Date 2021/7/9 20:56
 * @Description
 */
@Data
public class ModifyPasswordRequest {

    /**
     * 旧密码
     */
    @NotNull(message = "当前密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度必须是6-18位")
    private String currentPassword;

    /**
     * 新密码
     */
    @NotNull(message = "新密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度必须是6-18位")
    private String modifyPassword;

}
