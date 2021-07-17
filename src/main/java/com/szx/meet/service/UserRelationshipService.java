package com.szx.meet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szx.meet.entity.UserRelationship;
import com.szx.meet.vo.UserRlationShipRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author szx
 * @since 2021-07-17
 */
public interface UserRelationshipService extends IService<UserRelationship> {

    /**
     * 功能描述:建立关系
     *
     * @param userRlationShipRequest userRlationShipRequest
     * @return void
     * @author szx
     * @date 2021/7/17 19:30
     */
    void createShip(UserRlationShipRequest userRlationShipRequest);
}
