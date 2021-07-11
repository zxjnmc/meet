package com.szx.meet.controller;


import com.szx.meet.ao.AddUserTagReqeust;
import com.szx.meet.response.ApiResult;
import com.szx.meet.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author szx
 * @since 2021-07-11
 */
@RestController
@RequestMapping("/user-tag")
public class UserTagController {

    @Autowired
    private UserTagService userTagService;

    @PostMapping("link-to-user")
    public ApiResult<Void> addUserTag(@RequestBody AddUserTagReqeust addUserTagReqeust){
        userTagService.addUserTag(addUserTagReqeust);
        return ApiResult.success();
    }

}

