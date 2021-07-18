package com.szx.meet.controller;


import com.szx.meet.ao.LikeMeRequest;
import com.szx.meet.ao.MyLikeRequest;
import com.szx.meet.page.PageInfo;
import com.szx.meet.response.ApiResult;
import com.szx.meet.service.UserRelationshipService;
import com.szx.meet.vo.UserLikeMeResponse;
import com.szx.meet.vo.UserMyLikeResponse;
import com.szx.meet.vo.UserRelationshipRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author szx
 * @since 2021-07-17
 */
@RestController
@RequestMapping("/user-relationship")
public class UserRelationshipController {

    @Autowired
    private UserRelationshipService userRelationshipService;

    @PostMapping("/create-ship")
    public ApiResult<Void> createShip(@RequestBody UserRelationshipRequest userRelationshipRequest) {
        userRelationshipService.createShip(userRelationshipRequest);
        return ApiResult.success();
    }

    @GetMapping("/like-me")
    public ApiResult<PageInfo<UserLikeMeResponse>> likeMe(LikeMeRequest likeMeRequest) {
        return ApiResult.success(userRelationshipService.likeMe(likeMeRequest));
    }

    @GetMapping("/my-like")
    public ApiResult<PageInfo<UserMyLikeResponse>> myLike(MyLikeRequest myLikeRequest){
        return ApiResult.success(userRelationshipService.myLike(myLikeRequest));
    }

}

