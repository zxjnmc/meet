package com.szx.meet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szx.meet.ao.LikeMeRequest;
import com.szx.meet.ao.MyLikeRequest;
import com.szx.meet.entity.UserRelationship;
import com.szx.meet.page.PageInfo;
import com.szx.meet.vo.UserLikeMeResponse;
import com.szx.meet.vo.UserMyLikeResponse;
import com.szx.meet.vo.UserRelationshipRequest;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author szx
 * @since 2021-07-17
 */
public interface UserRelationshipService extends IService<UserRelationship> {

    /**
     * 功能描述:建立关系
     *
     * @param userRelationshipRequest userRlationShipRequest
     * @return void
     * @author szx
     * @date 2021/7/17 19:30
     */
    void createShip(UserRelationshipRequest userRelationshipRequest);

    /**
     * 功能描述:判断是否已经建立关系
     *
     * @param sourceUserId sourceUserId
     * @param targetUserId targetUserId
     * @return com.szx.meet.entity.UserRelationship
     * @author szx
     * @date 2021/7/17 20:13
     */
    UserRelationship judgeExistShip(Integer sourceUserId, Integer targetUserId);

    /**
     * 功能描述:是否彼此喜欢，包括超级喜欢
     *
     * @param sourceUserIds sourceUserIds
     * @param targetUserIds targetUserIds
     * @return java.util.List<com.szx.meet.entity.UserRelationship>
     * @author szx
     * @date 2021/7/18 00:15
     */
    List<UserRelationship> likeMutully(List<Integer> sourceUserIds, List<Integer> targetUserIds);

    /**
     * 功能描述:喜欢我的
     *
     * @param likeMeRequest likeMeRequest
     * @return com.szx.meet.page.PageInfo<com.szx.meet.vo.UserLikeMeResponse>
     * @author szx
     * @date 2021/7/17 21:18
     */
    PageInfo<UserLikeMeResponse> likeMe(LikeMeRequest likeMeRequest);

    /**
     * 功能描述:我喜欢的
     *
     * @param myLikeRequest myLikeRequest
     * @return com.szx.meet.page.PageInfo<com.szx.meet.vo.UserMyLikeResponse>
     * @author szx
     * @date 2021/7/18 02:04
     */
    PageInfo<UserMyLikeResponse> myLike(MyLikeRequest myLikeRequest);
}
