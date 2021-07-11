package com.szx.meet.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author szx
 * @Date 2021/7/11 11:05
 * @Description
 */
@Data
public class PersonTagResponse {

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级标签
     */
    private Integer parentId;

    /**
     * 图标地址
     */
    private String iconUrl;
}
