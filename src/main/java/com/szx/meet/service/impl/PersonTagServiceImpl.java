package com.szx.meet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szx.meet.ao.AddTagRequest;
import com.szx.meet.ao.UpdateTagRequest;
import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.entity.PersonTag;
import com.szx.meet.enums.LogicTypeEnum;
import com.szx.meet.exception.BizException;
import com.szx.meet.mapper.PersonTagMapper;
import com.szx.meet.service.PersonTagService;
import com.szx.meet.util.LocalDateTimeUtils;
import com.szx.meet.util.ValidatorUtils;
import com.szx.meet.vo.PersonTagResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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
public class PersonTagServiceImpl extends ServiceImpl<PersonTagMapper, PersonTag> implements PersonTagService {

    @Override
    public void add(AddTagRequest addTagRequest) {
        ValidatorUtils.validate(addTagRequest);
        //先查询是否存在
        PersonTag existPersonTag = getByName(addTagRequest.getName());
        if (!Objects.isNull(existPersonTag)) {
            throw new BizException(BizErrorCode.PERSON_TAG_EXISTS);
        }
        PersonTag personTag = new PersonTag();
        personTag.setName(addTagRequest.getName());
        personTag.setParentId(addTagRequest.getParentId());
        save(personTag);
    }

    @Override
    public void delete(Integer id) {
        PersonTag personTag = getById(id);
        if (Objects.isNull(personTag)) {
            throw new BizException(BizErrorCode.PERSON_TAG_NOT_EXISTS);
        }
        personTag.setUpdateTime(LocalDateTimeUtils.getCurrentDateTime());
        personTag.setIsDelete(LogicTypeEnum.YES.getValue());
        updateById(personTag);
    }

    @Override
    public void update(UpdateTagRequest updateTagRequest) {
        ValidatorUtils.validate(updateTagRequest);
        Integer id = updateTagRequest.getId();
        String name = updateTagRequest.getName();
        PersonTag byId = getById(id);
        if (Objects.isNull(byId)) {
            throw new BizException(BizErrorCode.PERSON_TAG_NOT_EXISTS);
        }
        PersonTag byName = getByName(name);
        if (!Objects.isNull(byName)) {
            throw new BizException(BizErrorCode.PERSON_TAG_EXISTS);
        }
        if (StringUtils.hasText(name)) {
            byId.setName(name);
            byId.setUpdateTime(LocalDateTimeUtils.getCurrentDateTime());
        }
        updateById(byId);
    }

    @Override
    public PersonTag getByName(String name) {
        LambdaQueryWrapper<PersonTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PersonTag::getName, name);
        return getOne(queryWrapper);
    }

    @Override
    public List<PersonTagResponse> listAll() {
        return null;
    }

}
