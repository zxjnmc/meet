package com.szx.meet.ao;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author szx
 * @Date 2021/7/11 10:27
 * @Description
 */
@Data
public class UpdateTagRequest {

    @NotNull(message = "标签id不能为空")
    private Integer id;

    private String name;

}
