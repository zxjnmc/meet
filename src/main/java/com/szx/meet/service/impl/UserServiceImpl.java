package com.szx.meet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szx.meet.entity.User;
import com.szx.meet.mapper.UserMapper;
import com.szx.meet.page.PageInfo;
import com.szx.meet.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author szx
 * @since 2021-07-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> listByCondition() {
        Page<User> page = new Page<>(1, 10);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        PageInfo<User> userPageInfo = new PageInfo<>();
        userPageInfo.setTotalRows(userIPage.getTotal());
        userPageInfo.setRecords(userIPage.getRecords());
        userPageInfo.setCurrentPage(page.getCurrent());
        userPageInfo.setPageSize(page.getSize());
        userPageInfo.setTotalRows(userIPage.getTotal());
        return userPageInfo;
    }
}
