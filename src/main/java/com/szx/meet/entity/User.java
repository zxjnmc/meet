package com.szx.meet.entity;

import com.szx.meet.base.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author szx
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 性别:0-未知 2-男 2-女
     */
    private Boolean gender;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 是否启用 0-不可用 1-可用
     */
    @TableLogic
    private Boolean isUse;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 创建人id
     */
    private Integer createId;

    /**
     * 更新人id
     */
    private Integer updateId;


}
