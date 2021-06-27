package com.szx.meet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

/**
 * @Author szx
 * @Date 2021/3/28 15:25
 * @Description
 */
@Import({RequestLogFilter.class, ThreadLocalFilter.class, WebSwitchFilter.class})
@Configuration
public class FiltersRegister {

    private final Logger logger = LoggerFactory.getLogger(FiltersRegister.class);

    public FiltersRegister() {
        logger.info("-----------> enable panda api filter");
    }

    @Bean
    public FilterRegistrationBean requestLogFilterRegistration(RequestLogFilter requestLogFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(requestLogFilter);
        registration.addUrlPatterns("/*");
        registration.setName("requestLogFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE - 8);
        return registration;
    }

    @Bean
    public FilterRegistrationBean threadLocalFilterRegistration(ThreadLocalFilter threadLocalFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(threadLocalFilter);
        registration.addUrlPatterns("/*");
        registration.setName("threadLocalFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
        return registration;
    }

    @Bean
    public FilterRegistrationBean webSwitchFilterRegistration(WebSwitchFilter webSwitchFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(webSwitchFilter);
        registration.addUrlPatterns("/*");
        registration.setName("webSwitchFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE - 12);
        return registration;
    }
}
