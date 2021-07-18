package com.szx.meet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szx.meet.ao.LikeMeRequest;
import com.szx.meet.ao.MyLikeRequest;
import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.entity.User;
import com.szx.meet.entity.UserRelationship;
import com.szx.meet.enums.LogicTypeEnum;
import com.szx.meet.enums.UserRelationshipTypeEnum;
import com.szx.meet.exception.BizException;
import com.szx.meet.mapper.UserRelationshipMapper;
import com.szx.meet.page.PageInfo;
import com.szx.meet.service.UserRelationshipService;
import com.szx.meet.service.UserService;
import com.szx.meet.thread.ThreadLocalRequest;
import com.szx.meet.util.ValidatorUtils;
import com.szx.meet.vo.UserLikeMeResponse;
import com.szx.meet.vo.UserMyLikeResponse;
import com.szx.meet.vo.UserRelationshipRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author szx
 * @since 2021-07-17
 */
@Service
@Slf4j
public class UserRelationshipServiceImpl extends ServiceImpl<UserRelationshipMapper, UserRelationship> implements UserRelationshipService {

    @Autowired
    private UserService userService;

    @Override
    public void createShip(UserRelationshipRequest userRelationshipRequest) {
        ValidatorUtils.validate(userRelationshipRequest);
        Integer targetUserId = userRelationshipRequest.getTargetUserId();
        Integer type = userRelationshipRequest.getType();
        Integer sourceUserId = ThreadLocalRequest.getUserId();
        if (Objects.equals(sourceUserId, targetUserId)) {
            log.error("不能喜欢自己");
            throw new BizException(BizErrorCode.CAN_NOT_LIKE_YOUSELFT);
        }
        User targetUser = userService.getById(targetUserId);
        User user = userService.getById(sourceUserId);
        if (Objects.isNull(targetUser) || Objects.isNull(user)) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTS);
        }
        //判断源用户对目标用户是否已经建立关系
        UserRelationship existUserRelationship = judgeExistShip(sourceUserId, targetUserId);
        if (Objects.isNull(existUserRelationship)) {
            UserRelationship userRelationship = new UserRelationship();
            userRelationship.setSourceUserId(sourceUserId);
            userRelationship.setTargetUserId(targetUserId);
            userRelationship.setType(type);
            save(userRelationship);
        } else {
            existUserRelationship.setType(type);
            updateById(existUserRelationship);
        }
    }

    @Override
    public UserRelationship judgeExistShip(Integer sourceUserId, Integer targetUserId) {
        LambdaQueryWrapper<UserRelationship> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRelationship::getSourceUserId, sourceUserId);
        queryWrapper.eq(UserRelationship::getTargetUserId, targetUserId);
        return getOne(queryWrapper);
    }

    @Override
    public List<UserRelationship> likeMutully(List<Integer> sourceUserIds, List<Integer> targetUserIds) {
//        return judgeLikeTargetUser(sourceUserIds, targetUserIds) && judgeLikeTargetUser(targetUserIds, sourceUserIds);
        return null;
    }

    @Override
    public PageInfo<UserLikeMeResponse> likeMe(LikeMeRequest likeMeRequest) {
        Integer userId = ThreadLocalRequest.getUserId();
        LambdaQueryWrapper<UserRelationship> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRelationship::getTargetUserId, userId);
        queryWrapper.and(wrapper -> wrapper.eq(UserRelationship::getType, UserRelationshipTypeEnum.LIKE.getValue()).
                or().eq(UserRelationship::getType, UserRelationshipTypeEnum.SUPER_LIKE.getValue()));
        IPage<UserRelationship> page = page(new Page<>(likeMeRequest.getCurrentPage(), likeMeRequest.getPageSize()), queryWrapper);
        //查询出喜欢我的
        List<UserRelationship> records = page.getRecords();
        //喜欢我的userIds
        List<Integer> likeMeIds = records.stream().map(UserRelationship::getSourceUserId).collect(Collectors.toList());
        //我喜欢的
        List<UserRelationship> userRelationships = partMyLike(userId, likeMeIds);
        Map<Integer, UserRelationship> myLikeMap = userRelationships.stream().collect(Collectors.toMap(UserRelationship::getTargetUserId, x -> x));
        List<UserLikeMeResponse> userLikeMeResponses = new ArrayList<>();
        records.forEach(x -> {
            UserLikeMeResponse userLikeMeResponse = new UserLikeMeResponse();
            userLikeMeResponse.setUserId(x.getSourceUserId());
            userLikeMeResponse.setDescription("暂无描述");
            UserRelationship userRelationship = myLikeMap.get(x.getSourceUserId());
            int likeMutully = Objects.isNull(userRelationship) ? LogicTypeEnum.NO.getValue() : LogicTypeEnum.YES.getValue();
            userLikeMeResponse.setLikeMutully(likeMutully);
            userLikeMeResponses.add(userLikeMeResponse);
        });
        PageInfo<UserLikeMeResponse> userRelationshipPageInfo = new PageInfo<>();
        userRelationshipPageInfo.setTotalPage(page.getTotal());
        userRelationshipPageInfo.setCurrentPage(page.getCurrent());
        userRelationshipPageInfo.setRecords(userLikeMeResponses);
        userRelationshipPageInfo.setPageSize(page.getSize());
        userRelationshipPageInfo.setTotalCount(page.getTotal());
        return userRelationshipPageInfo;
    }

    @Override
    public PageInfo<UserMyLikeResponse> myLike(MyLikeRequest myLikeRequest) {
        Integer userId = ThreadLocalRequest.getUserId();
        IPage<UserRelationship> userRelationshipIPage = allLike(userId, myLikeRequest.getCurrentPage(), myLikeRequest.getPageSize());
        PageInfo<UserMyLikeResponse> userMyLikeResponsePageInfo = new PageInfo<>();
        userMyLikeResponsePageInfo.setPageSize(userRelationshipIPage.getSize());
        userMyLikeResponsePageInfo.setCurrentPage(userRelationshipIPage.getCurrent());
        userMyLikeResponsePageInfo.setTotalCount(userRelationshipIPage.getTotal());
        userMyLikeResponsePageInfo.setTotalPage(userRelationshipIPage.getTotal());
        //查询出我喜欢的
        List<UserRelationship> records = userRelationshipIPage.getRecords();
        List<Integer> myLikeIds = records.stream().map(UserRelationship::getTargetUserId).collect(Collectors.toList());
        List<UserRelationship> userRelationships = partLikeMe(myLikeIds, userId);
        Map<Integer, UserRelationship> myLikeMap = userRelationships.stream().collect(Collectors.toMap(UserRelationship::getSourceUserId, x -> x));
        List<UserMyLikeResponse> userMyLikeResponses = new ArrayList<>();
        records.forEach(x -> {
            UserMyLikeResponse userMyLikeResponse = new UserMyLikeResponse();
            userMyLikeResponse.setUserId(x.getTargetUserId());
            userMyLikeResponse.setDescription("暂无描述");
            UserRelationship userRelationship = myLikeMap.get(x.getTargetUserId());
            int likeMutully = Objects.isNull(userRelationship) ? LogicTypeEnum.NO.getValue() : LogicTypeEnum.YES.getValue();
            userMyLikeResponse.setLikeMutully(likeMutully);
            userMyLikeResponses.add(userMyLikeResponse);
        });
        userMyLikeResponsePageInfo.setRecords(userMyLikeResponses);
        return userMyLikeResponsePageInfo;
    }


    /**
     * 功能描述:查询出sourceUserId对targetUserIds是否喜欢
     *
     * @param sourceUserId  sourceUserId
     * @param targetUserIds targetUserIds
     * @return java.util.List<com.szx.meet.entity.UserRelationship>
     * @author szx
     * @date 2021/7/18 00:33
     */
    private List<UserRelationship> partMyLike(Integer sourceUserId, List<Integer> targetUserIds) {
        LambdaQueryWrapper<UserRelationship> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRelationship::getSourceUserId, sourceUserId);
        queryWrapper.in(UserRelationship::getTargetUserId, targetUserIds);
        queryWrapper.and(wrapper -> wrapper.eq(UserRelationship::getType, UserRelationshipTypeEnum.LIKE.getValue())
                .or().eq(UserRelationship::getType, UserRelationshipTypeEnum.SUPER_LIKE.getValue()));
        return list(queryWrapper);
    }

    private List<UserRelationship> partLikeMe(List<Integer> sourceUserIds, Integer targetUserId) {
        LambdaQueryWrapper<UserRelationship> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(UserRelationship::getSourceUserId, sourceUserIds);
        queryWrapper.eq(UserRelationship::getTargetUserId, targetUserId);
        queryWrapper.and(wrapper -> wrapper.eq(UserRelationship::getType, UserRelationshipTypeEnum.LIKE.getValue())
                .or().eq(UserRelationship::getType, UserRelationshipTypeEnum.SUPER_LIKE.getValue()));
        return list(queryWrapper);
    }

    public IPage<UserRelationship> allLike(Integer targetUserId, Long currentPage, Long pageSize) {
        LambdaQueryWrapper<UserRelationship> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRelationship::getSourceUserId, targetUserId);
        queryWrapper.and(wrapper -> wrapper.eq(UserRelationship::getType, UserRelationshipTypeEnum.LIKE.getValue())
                .or().eq(UserRelationship::getType, UserRelationshipTypeEnum.SUPER_LIKE.getValue()));
        return page(new Page<>(currentPage, pageSize), queryWrapper);
    }

}
