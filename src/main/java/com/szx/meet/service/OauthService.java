package com.szx.meet.service;

import com.szx.meet.ao.UserRequest;
import com.szx.meet.vo.UserVO;

/**
 * @Author szx
 * @Date 2021/3/28 01:22
 * @Description 授权业务处理
 */
public interface OauthService {

    /**
     *
     * 功能描述:用户登录
     * @param userRequest userAO
     * @return com.szx.meet.vo.UserVO
     * @author szx
     * @date 2021/3/28 01:30
     */
    UserVO login(UserRequest userRequest);
}
