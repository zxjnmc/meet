package com.szx.meet.service.impl;

import com.szx.meet.ao.UserRequest;
import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.enums.ChannelTypeEnum;
import com.szx.meet.exception.BizException;
import com.szx.meet.service.OauthService;
import com.szx.meet.util.EnumUtils;
import com.szx.meet.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author szx
 * @Date 2021/7/4 14:15
 * @Description
 */
@Service
@Slf4j
public class OauthServiceImpl implements OauthService {

    @Override
    public UserResponse login(UserRequest userRequest) {
        Integer channelType = userRequest.getChannelType();
        if (!Objects.equals(ChannelTypeEnum.PHONE_PWD.getValue(), channelType)) {
            log.info("登录方式有误！");
            throw new BizException(BizErrorCode.LOGIN_CHANNEL_ERROR);
        }
        String msg = EnumUtils.getByCode(channelType, ChannelTypeEnum.class);
        log.info("msg:{}", msg);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(1);
        userResponse.setChannel(channelType);
        userResponse.setAccessToken("hjfjdnbf");
        return userResponse;
    }

}
