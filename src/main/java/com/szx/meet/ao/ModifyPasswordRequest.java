package com.szx.meet.ao;


import lombok.Data;

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
    private String password;

    /**
     * 新密码
     */
    private String newPassword;

}
