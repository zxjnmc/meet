package com.szx.meet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @since 2021-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 源用户id
     */
    private Integer sourceUserId;

    /**
     * 目标用户id
     */
    private Integer targetUserId;

    /**
     * 逻辑删除: 1-是 0-否
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 类型: 1-喜欢 2-超级喜欢 3-无感 4-拉黑
     */
    private Integer type;

    /**
     * 是否聊天: 0-否 1-是 
     */
    private Integer isChat;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 乐观锁版本号
     */
    @Version
    private Integer version;


}
