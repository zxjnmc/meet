package com.szx.meet.service.impl;

import com.szx.meet.ao.ModifyPasswordRequest;
import com.szx.meet.ao.RetrievePasswordRequest;
import com.szx.meet.ao.UserRequest;
import com.szx.meet.constants.LoginConstants;
import com.szx.meet.entity.User;
import com.szx.meet.entity.UserLoginLog;
import com.szx.meet.enums.LogicTypeEnum;
import com.szx.meet.service.AccessTokenService;
import com.szx.meet.service.OauthService;
import com.szx.meet.service.UserLoginLogService;
import com.szx.meet.service.UserService;
import com.szx.meet.util.EncryptionUtils;
import com.szx.meet.util.RedisUtils;
import com.szx.meet.util.RegexUtils;
import com.szx.meet.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

/**
 * @Author szx
 * @Date 2021/7/4 14:15
 * @Description
 */
@Service
@Slf4j
public class OauthServiceImpl implements OauthService {

    @Autowired
    private UserService userService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private UserLoginLogService userLoginLogService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponse login(UserRequest userRequest) {
        String phone = userRequest.getPhone();
        Integer channelType = userRequest.getChannelType();
        RegexUtils.regexPhone(phone);
        User user = userService.getByPhone(phone);
        Integer isNew = LogicTypeEnum.NO.getValue();
        Integer id = null;
        if (Objects.isNull(user)) {
            //注册流程
            isNew = LogicTypeEnum.YES.getValue();
            User saveUser = new User();
            saveUser.setPhone(phone);
            saveUser.setPassword(EncryptionUtils.encryForSha(userRequest.getPassword()));
            userService.save(saveUser);
            id = saveUser.getId();
        }
        Integer userId = Objects.isNull(user) ? id : user.getId();
        //登录流程
        String loginInfo = String.format(LoginConstants.LOGIN_INFO, userId);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String accessToken = String.format(LoginConstants.ACCESS_TOKEN, uuid);
        UserResponse userResponse = new UserResponse();
        userResponse.setIsNew(isNew);
        userResponse.setUserId(userId);
        userResponse.setChannel(channelType);
        userResponse.setAccessToken(accessToken);
        //将之前的loginInfo key过期
        Object o = redisUtils.getValue(loginInfo);
        if (!Objects.isNull(o)) {
            redisUtils.delKey(loginInfo);
            redisUtils.delKey(o.toString());
        }
        //做登录记录
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setUserId(userId);
        userLoginLog.setChannelType(channelType);
        userLoginLogService.save(userLoginLog);
        //设置token
        accessTokenService.setAccessToken(loginInfo, accessToken, userResponse);
        return userResponse;
    }

    @Override
    public void logout() {

    }

    @Override
    public void mdifyPassword(ModifyPasswordRequest modifyPasswordRequest) {

    }

    @Override
    public void retrievePassword(RetrievePasswordRequest retrievePasswordRequest) {

    }

}
