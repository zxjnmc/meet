package com.szx.meet.service.impl;

import com.szx.meet.entity.User;
import com.szx.meet.mapper.UserMapper;
import com.szx.meet.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author szx
 * @since 2021-07-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }
}
