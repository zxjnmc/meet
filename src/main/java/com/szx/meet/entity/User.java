package com.szx.meet.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author szx
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;
//    /**
//     * 手机号
//     */
//    @TableField("phone_num")
//    private String phoneNum;
//    /**
//     * 邮箱
//     */
//    private String email;
//    /**
//     * 更新日期
//     */
//    @TableField("update_time")
//    private Date updateTime;
//    /**
//     * 创建日期
//     */
//    @TableField("create_time")
//    private Date createTime;
//

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
