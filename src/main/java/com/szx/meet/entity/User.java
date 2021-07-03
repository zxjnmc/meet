package com.szx.meet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author szx
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 性别:0-未知 2-男 2-女
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
     * 是否启用 0-不可用 1-可用
     */
    @TableLogic
    private Integer isUse;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Version
    private Integer version;

}
