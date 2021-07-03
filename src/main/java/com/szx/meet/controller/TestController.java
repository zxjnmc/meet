package com.szx.meet.controller;

import com.szx.meet.annotation.AccessToken;
import com.szx.meet.entity.User;
import com.szx.meet.response.Result;
import com.szx.meet.service.UserService;
import com.szx.meet.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Author szx
 * @Date 2021/3/15 23:25
 * @Description 测试控制器
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public String get(String value) {
        return value;
    }

    @GetMapping("/set")
    public void set(String key, String value) {
        redisUtils.setValue(key, value);
    }

    @GetMapping("/getValue")
    public String getValue(String key) {
        Object value = redisUtils.getValue(key);
        if (Objects.isNull(value)) {
            return null;
        }
        return value.toString();
    }

    @GetMapping("/del")
    public Result<Boolean> delKey(String key) {
        return Result.success(redisUtils.delKey(key));
    }

    @AccessToken
    @GetMapping("/getUser")
    public Result<User> getUser(Integer id) {
        User user = userService.selectById(id);
        System.out.println(user.toString());
        return Result.success(user);
    }

}
