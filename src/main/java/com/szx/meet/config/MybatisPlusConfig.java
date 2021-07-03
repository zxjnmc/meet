package com.szx.meet.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author szx
 * @Date 2021/7/3 10:26
 * @Description
 */
@Configuration
@MapperScan("xom.szx.meet.mapper")
public class MybatisPlusConfig {

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    //逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

}
