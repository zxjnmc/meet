package com.szx.meet.service;

import com.szx.meet.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szx.meet.page.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author szx
 * @since 2021-07-03
 */
public interface UserService extends IService<User> {

    PageInfo<User> listByCondition();

}
