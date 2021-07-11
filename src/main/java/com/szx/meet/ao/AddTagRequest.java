package com.szx.meet.ao;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author szx
 * @Date 2021/7/11 10:19
 * @Description
 */
@Data
public class AddTagRequest {

    /**
     * 标签名称
     */
    @NotNull(message = "标签名称不能为空")
    private String name;

    /**
     * 父级标签，顶级为0
     */
    private Integer parentId;
}
