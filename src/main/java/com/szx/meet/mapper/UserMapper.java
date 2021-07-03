package com.szx.meet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szx.meet.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author szx
 * @since 2021-07-03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
