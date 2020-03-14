package com.mingben.betplatform.config;

import com.mingben.betplatform.filter.LoginFilter;
import com.mingben.betplatform.service.AdminService;
import com.mingben.betplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@Configuration
public class ServletFilter {

    @Autowired
    private LoginFilter loginFilter;

    /**
     * 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean loginFilterRegistration(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(loginFilter);
        return filterRegistrationBean;
    }

}
