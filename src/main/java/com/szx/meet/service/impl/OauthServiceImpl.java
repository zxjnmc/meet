package com.szx.meet.service.impl;

import com.szx.meet.ao.ModifyPasswordRequest;
import com.szx.meet.ao.RetrievePasswordRequest;
import com.szx.meet.ao.SendVerifyCodeRequest;
import com.szx.meet.ao.UserRequest;
import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.constants.RedisConstants;
import com.szx.meet.entity.User;
import com.szx.meet.entity.UserLoginLog;
import com.szx.meet.enums.LogicTypeEnum;
import com.szx.meet.exception.BizException;
import com.szx.meet.service.AccessTokenService;
import com.szx.meet.service.OauthService;
import com.szx.meet.service.UserLoginLogService;
import com.szx.meet.service.UserService;
import com.szx.meet.thread.ThreadLocalRequest;
import com.szx.meet.util.*;
import com.szx.meet.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    public UserResponse login(UserRequest userRequest, HttpServletRequest request) {
        ValidatorUtils.validate(userRequest);
        String phone = userRequest.getPhone();
        RegexUtils.regexPhone(phone);
        Integer channelType = userRequest.getChannelType();
        String password = userRequest.getPassword();
        User user = userService.getByPhone(phone);
        Integer isNew = LogicTypeEnum.NO.getValue();
        Integer userId;
        if (Objects.isNull(user)) {
            //注册流程
            isNew = LogicTypeEnum.YES.getValue();
            userId = register(phone, password);
        } else {
            //校验密码
            verifyPassword(password, user);
            userId = user.getId();
        }
        //生成token
        String loginInfo = getLoginInfo(userId);
        String accessToken = String.format(RedisConstants.ACCESS_TOKEN, UUID.randomUUID().toString().replace("-", ""));
        UserResponse userResponse = getUserResponse(channelType, isNew, userId, accessToken, phone);
        //将之前的loginInfo key过期
        removeAccessToken(loginInfo);
        //做登录记录
        saveLoginLog(request, channelType, userId);
        //设置token
        accessTokenService.setAccessToken(loginInfo, accessToken, userResponse);
        return userResponse;
    }

    @Override
    public void logout() {
        removeAccessToken(getLoginInfo(ThreadLocalRequest.getUserId()));
        log.info("用户[{}]已退出登录", ThreadLocalRequest.getPhone());
    }

    @Override
    public void modifyPassword(ModifyPasswordRequest modifyPasswordRequest) {
        ValidatorUtils.validate(modifyPasswordRequest);
        Integer userId = ThreadLocalRequest.getUserId();
        User user = userService.getById(userId);
        if (Objects.isNull(user)) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTS);
        }
        String currentPassword = modifyPasswordRequest.getCurrentPassword();
        String modifyPasword = modifyPasswordRequest.getModifyPassword();
        if (Objects.equals(currentPassword, modifyPasword)) {
            throw new BizException(BizErrorCode.PASSWORD_SAME);
        }
        String encryPassword = EncryptionUtils.encryForSha(currentPassword);
        if (!Objects.equals(encryPassword, user.getPassword())) {
            throw new BizException(BizErrorCode.PASSWORD_ERROR);
        }
        user.setPassword(EncryptionUtils.encryForSha(modifyPasswordRequest.getModifyPassword()));
        user.setUpdateTime(LocalDateTimeUtils.getCurrentDateTime());
        userService.updateById(user);
        removeAccessToken(getLoginInfo(userId));
        log.info("用户[{}]密码修改完成", ThreadLocalRequest.getPhone());
    }

    @Override
    public void retrievePassword(RetrievePasswordRequest retrievePasswordRequest) {
        ValidatorUtils.validate(retrievePasswordRequest);
        String phone = retrievePasswordRequest.getPhone();
        RegexUtils.regexPhone(phone);
        String newPassword = retrievePasswordRequest.getNewPassword();
        String verifyCode = retrievePasswordRequest.getVerifyCode();
        User user = userService.getByPhone(phone);
        if (Objects.isNull(user)) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTS);
        }
        String retrievePasswordKey = String.format(RedisConstants.RETRIEVE_PASSWORD_INFO, phone);
        Object value = redisUtils.getValue(retrievePasswordKey);
        if (Objects.isNull(value)) {
            throw new BizException(BizErrorCode.VERIFY_CODE_NOT_EXISTS);
        }
        String existVerifyCode = String.valueOf(value);
        if (!Objects.equals(verifyCode, existVerifyCode)) {
            throw new BizException(BizErrorCode.VERIFY_CODE_ERROR);
        }
        user.setPassword(EncryptionUtils.encryForSha(newPassword));
        userService.updateById(user);
        redisUtils.delKey(retrievePasswordKey);
    }

    @Override
    public String sendVerifyCode(SendVerifyCodeRequest sendVerifyCodeRequest) {
        String phone = sendVerifyCodeRequest.getPhone();
        ValidatorUtils.validate(sendVerifyCodeRequest);
        RegexUtils.regexPhone(phone);
        User user = userService.getByPhone(phone);
        if (Objects.isNull(user)) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTS);
        }
        String randomNum = RandomUtils.generateRandomNum(6);
        log.info("验证码:{}", randomNum);
        redisUtils.setValueExpire(String.format(RedisConstants.RETRIEVE_PASSWORD_INFO, phone), randomNum, RedisConstants.RETRIEVE_PASSWORD_TTL, TimeUnit.SECONDS);
        return randomNum;
    }

    /**
     * 功能描述:获取用户返回信息
     *
     * @param channelType channelType
     * @param isNew       isNew
     * @param userId      userId
     * @param accessToken accessToken
     * @return com.szx.meet.vo.UserResponse
     * @author szx
     * @date 2021/7/10 09:46
     */
    private UserResponse getUserResponse(Integer channelType, Integer isNew, Integer userId, String accessToken, String phone) {
        UserResponse userResponse = new UserResponse();
        userResponse.setIsNew(isNew);
        userResponse.setUserId(userId);
        userResponse.setChannelType(channelType);
        userResponse.setPhone(phone);
        userResponse.setAccessToken(accessToken);
        return userResponse;
    }

    /**
     * 功能描述:获取登录信息
     *
     * @param userId userId
     * @return java.lang.String
     * @author szx
     * @date 2021/7/10 09:46
     */
    private String getLoginInfo(Integer userId) {
        return String.format(RedisConstants.LOGIN_INFO, userId);
    }

    /**
     * 功能描述:移除accesstoken
     *
     * @param loginInfo loginInfo
     * @return void
     * @author szx
     * @date 2021/7/10 09:38
     */
    private void removeAccessToken(String loginInfo) {
        Object o = redisUtils.getValue(loginInfo);
        if (!Objects.isNull(o)) {
            redisUtils.delKey(loginInfo);
            redisUtils.delKey(o.toString());
        }
    }

    /**
     * 功能描述:校验密码
     *
     * @param password password
     * @param user     user
     * @return void
     * @author szx
     * @date 2021/7/10 09:32
     */
    private void verifyPassword(String password, User user) {
        String encryForSha = EncryptionUtils.encryForSha(password);
        if (!Objects.equals(encryForSha, user.getPassword())) {
            throw new BizException(BizErrorCode.ACCOUNT_ERROR);
        }
    }

    /**
     * 功能描述:注册流程
     *
     * @param phone    phone
     * @param password password
     * @return java.lang.Integer
     * @author szx
     * @date 2021/7/10 09:32
     */
    private Integer register(String phone, String password) {
        Integer userId;
        User saveUser = new User();
        saveUser.setPhone(phone);
        saveUser.setPassword(EncryptionUtils.encryForSha(password));
        userService.save(saveUser);
        userId = saveUser.getId();
        return userId;
    }

    /**
     * 功能描述:记录登录日志
     *
     * @param request     request
     * @param channelType channelType
     * @param userId      userId
     * @return void
     * @author szx
     * @date 2021/7/10 09:31
     */
    private void saveLoginLog(HttpServletRequest request, Integer channelType, Integer userId) {
        String userAgent = request.getHeader("user-agent");
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setUserId(userId);
        userLoginLog.setChannelType(channelType);
        userLoginLog.setOs(HttpUtils.getOs(userAgent));
        userLoginLog.setBrowser(HttpUtils.getBrowser(userAgent));
        userLoginLog.setIp(HttpUtils.getIp(request));
        userLoginLogService.save(userLoginLog);
    }

}
