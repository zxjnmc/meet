package com.szx.meet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

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
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 性别:0-未知 1-男 2-女
     */
    private Integer gender;

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
     * 是否删除 0-未删除 1-已删除
     */
    @TableLogic
    private Integer isDelete;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 乐观锁版本
     */
    @Version
    private Integer version;


}
