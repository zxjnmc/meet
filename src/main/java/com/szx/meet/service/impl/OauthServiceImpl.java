package com.szx.meet.service.impl;

import com.alibaba.fastjson.JSON;
import com.szx.meet.ao.UserRequest;
import com.szx.meet.consts.BizErrorCode;
import com.szx.meet.consts.RedisKeyConstants;
import com.szx.meet.entity.User;
import com.szx.meet.exception.BizException;
import com.szx.meet.mapper.UserMapper;
import com.szx.meet.service.OauthService;
import com.szx.meet.util.RedisUtils;
import com.szx.meet.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * @Author szx
 * @Date 2021/3/28 01:23
 * @Description
 */
@Service
public class OauthServiceImpl implements OauthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public UserVO login(UserRequest userRequest) {
        UserVO userVO = new UserVO();
        User user = new User();
        user.setName(userRequest.getName());
        // 根据用户名查询用户
        User existUser = userMapper.selectOne(user);
        if (Objects.isNull(existUser)) {
            //如果用户不存在，走注册逻辑
        } else {
            //如果用户存在
            //对比密码是否一致
            if (!Objects.equals(existUser.getPassword(), userRequest.getPassword())) {
                throw new BizException(BizErrorCode.ACCOUNT_ERROR);
            }
            //刷新token
            String loginKey = String.format(RedisKeyConstants.LOGIN_KEY, existUser.getId());
            Object value = redisUtils.getValue(loginKey);
            if (!Objects.isNull(value)) {
                redisUtils.delKey(loginKey);
            }
            String accessToken = UUID.randomUUID().toString();
            userVO.setName(existUser.getName());
            userVO.setAccessToken(accessToken);
//            userVO.setEmail(existUser.getEmail());
            userVO.setId(existUser.getId());
//            userVO.setPhoneNum(existUser.getPhoneNum());
            redisUtils.setValueExpire(loginKey,
                    String.format(RedisKeyConstants.LOGIN_KEY, accessToken),
                    RedisKeyConstants.LOGIN_EXPIRE_TIME);
            redisUtils.setValue(String.format(RedisKeyConstants.ACCESS_TOKEN, accessToken), JSON.toJSON(userVO));
        }
        return userVO;
    }

    public UserVO register(UserRequest ao) {
        return null;
    }


}
