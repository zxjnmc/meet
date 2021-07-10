package com.szx.meet.service;

import com.szx.meet.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szx.meet.page.PageInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author szx
 * @since 2021-07-03
 */
public interface UserService extends IService<User> {

    /**
     * 功能描述:根据手机号获取用户信息
     *
     * @param phone phone
     * @return com.szx.meet.entity.User
     * @author szx
     * @date 2021/7/10 13:48
     */
    User getByPhone(String phone);
}
