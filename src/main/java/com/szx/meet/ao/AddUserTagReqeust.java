package com.szx.meet.ao;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author szx
 * @Date 2021/7/11 12:51
 * @Description
 */
@Data
public class AddUserTagReqeust {

    /**
     * 标签id
     */
    @NotNull
    private Integer tagId;
}
