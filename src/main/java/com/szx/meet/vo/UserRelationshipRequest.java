package com.szx.meet.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author szx
 * @Date 2021/7/17 19:27
 * @Description
 */
@Data
public class UserRelationshipRequest {

    /**
     * 目标用户id
     */
    @NotNull(message = "目标用户id不能为空")
    private Integer targetUserId;

    /**
     * 1-喜欢 2-超级喜欢 3-无感 4-拉黑
     */
    @NotNull(message = "用户类型类型")
    private Integer type;

}
