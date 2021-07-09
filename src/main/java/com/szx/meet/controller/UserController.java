package com.szx.meet.controller;


import com.szx.meet.annotation.AccessToken;
import com.szx.meet.entity.User;
import com.szx.meet.page.PageInfo;
import com.szx.meet.response.ApiResult;
import com.szx.meet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author szx
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ApiResult<Void> add() {
        User user = new User();
        user.setName("szx");
        user.setGender(1);
        userService.save(user);
        return ApiResult.success();
    }

    @GetMapping("/get")
    @AccessToken
    public ApiResult<User> get() {
        return ApiResult.success(userService.getById());
    }

    @PostMapping("/update/{userId}")
    public ApiResult<Void> update(@PathVariable("userId") Integer userId) {
        User user = userService.getById(userId);
        user.setName("宋振兴");
        user.setId(userId);
        userService.updateById(user);
        return ApiResult.success();
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResult<Void> delete(@PathVariable Integer userId) {
        userService.removeById(userId);
        return ApiResult.success();
    }

    @GetMapping("/list")
    public ApiResult<PageInfo<User>> list() {
        PageInfo<User> userPageInfo = userService.listByCondition();
        return ApiResult.success(userPageInfo);
    }


}

