package com.szx.meet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szx.meet.entity.UserRelationship;
import com.szx.meet.mapper.UserRelationshipMapper;
import com.szx.meet.service.UserRelationshipService;
import com.szx.meet.vo.UserRlationShipRequest;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author szx
 * @since 2021-07-17
 */
@Service
public class UserRelationshipServiceImpl extends ServiceImpl<UserRelationshipMapper, UserRelationship> implements UserRelationshipService {

    @Override
    public void createShip(UserRlationShipRequest userRlationShipRequest) {

    }
}
