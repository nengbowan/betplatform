package com.mingben.betplatform.config;

import com.mingben.betplatform.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@Configuration
public class ServletFilter {

    /**
     * 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean loginFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LoginFilter());
        return filterRegistrationBean;
    }

}
