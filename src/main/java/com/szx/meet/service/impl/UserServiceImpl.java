package com.szx.meet.service.impl;

import com.szx.meet.entity.User;
import com.szx.meet.mapper.UserMapper;
import com.szx.meet.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author szx
 * @since 2021-03-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
