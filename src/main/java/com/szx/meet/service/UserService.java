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

    PageInfo<User> listByCondition();

    /**
     * 功能描述:根据手机号查询用户
     *
     * @param phone phone
     * @return com.szx.meet.entity.User
     * @author szx
     * @date 2021/7/9 21:54
     */
    User getByPhone(String phone);

}
