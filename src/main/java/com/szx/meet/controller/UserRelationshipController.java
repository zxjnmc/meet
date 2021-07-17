package com.szx.meet.controller;


import com.szx.meet.response.ApiResult;
import com.szx.meet.service.UserRelationshipService;
import com.szx.meet.vo.UserRlationShipRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ApiResult<Void> createShip(UserRlationShipRequest userRlationShipRequest) {
        userRelationshipService.createShip(userRlationShipRequest);
        return ApiResult.success();
    }

}

