package com.szx.meet.service;

import com.szx.meet.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author szx
 * @since 2021-07-03
 */
public interface UserService extends IService<User> {

    void add(User user);

}
