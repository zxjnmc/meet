package com.szx.meet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author szx
 * @since 2021-07-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * os
     */
    private String os;

    /**
     * 登录类型
     */
    private Integer channelType;

    /**
     * 登录时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
