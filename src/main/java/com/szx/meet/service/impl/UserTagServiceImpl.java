package com.szx.meet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szx.meet.ao.AddUserTagReqeust;
import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.entity.PersonTag;
import com.szx.meet.entity.User;
import com.szx.meet.entity.UserTag;
import com.szx.meet.enums.LogicTypeEnum;
import com.szx.meet.exception.BizException;
import com.szx.meet.mapper.UserTagMapper;
import com.szx.meet.service.PersonTagService;
import com.szx.meet.service.UserService;
import com.szx.meet.service.UserTagService;
import com.szx.meet.thread.ThreadLocalRequest;
import com.szx.meet.util.LocalDateTimeUtils;
import com.szx.meet.util.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author szx
 * @since 2021-07-11
 */
@Service
public class UserTagServiceImpl extends ServiceImpl<UserTagMapper, UserTag> implements UserTagService {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonTagService personTagService;

    @Override
    public void addUserTag(AddUserTagReqeust addUserTagReqeust) {
        ValidatorUtils.validate(addUserTagReqeust);
        Integer userId = ThreadLocalRequest.getUserId();
        Integer tagId = addUserTagReqeust.getTagId();
        User user = userService.getById(userId);
        if (Objects.isNull(user)) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTS);
        }
        PersonTag personTag = personTagService.getById(tagId);
        if (Objects.isNull(personTag)) {
            throw new BizException(BizErrorCode.PERSON_TAG_NOT_EXISTS);
        }
        if (Objects.equals(0, personTag.getParentId())) {
            throw new BizException(BizErrorCode.PARENT_TAG_NOT_LINK_USER);
        }
        UserTag userTag = getByUserIdAndTagId(userId, tagId);
        if (Objects.isNull(userTag)) {
            UserTag saveUserTag = new UserTag();
            saveUserTag.setTagId(tagId);
            saveUserTag.setUserId(userId);
            save(saveUserTag);
        } else {
            Integer enabled = Objects.equals(LogicTypeEnum.NO.getValue(), userTag.getEnabled()) ? LogicTypeEnum.YES.getValue() : LogicTypeEnum.NO.getValue();
            userTag.setUpdateTime(LocalDateTimeUtils.getCurrentDateTime());
            userTag.setEnabled(enabled);
            updateById(userTag);
        }
    }

    @Override
    public UserTag getByUserIdAndTagId(Integer userId, Integer tagId) {
        LambdaQueryWrapper<UserTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTag::getUserId, userId);
        queryWrapper.eq(UserTag::getTagId, tagId);
        return getOne(queryWrapper);
    }
}
