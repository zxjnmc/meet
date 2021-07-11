package com.szx.meet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szx.meet.ao.AddUserTagReqeust;
import com.szx.meet.entity.UserTag;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author szx
 * @since 2021-07-11
 */
public interface UserTagService extends IService<UserTag> {

    /**
     * 功能描述:用户和标签关联
     *
     * @param addUserTagReqeust addUserTagReqeust
     * @return void
     * @author szx
     * @date 2021/7/11 13:10
     */
    void addUserTag(AddUserTagReqeust addUserTagReqeust);

    /**
     * 功能描述:根据userId和tagId获取用户标签
     *
     * @param userId userId
     * @param tagId  tagId
     * @return com.szx.meet.entity.UserTag
     * @author szx
     * @date 2021/7/11 13:13
     */
    UserTag getByUserIdAndTagId(Integer userId, Integer tagId);

}
