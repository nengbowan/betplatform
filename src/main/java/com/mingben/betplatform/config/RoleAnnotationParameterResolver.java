package com.mingben.betplatform.config;

import com.mingben.betplatform.annotation.AdminAnno;
import com.mingben.betplatform.annotation.UserAnno;
import com.mingben.betplatform.entity.Admin;
import com.mingben.betplatform.entity.User;
import com.mingben.betplatform.service.AdminService;
import com.mingben.betplatform.service.UserService;
import com.mingben.betplatform.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;


/**
 * 方法参数自动注入
 */
@Component
public class RoleAnnotationParameterResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;



    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if(parameter.getParameterType() ==  Admin.class || parameter.getParameterType() ==  User.class){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {
        String token = request.getHeader("token");
        Map<String,String> decodeMap = JwtTokenUtil.getPayLoad(token);
        String username = decodeMap.get("username");
        String passwd = decodeMap.get("password");
        String type = decodeMap.get("type");

        if(type.equals("admin")){
            Admin admin = adminService.findByUsername(username);
            return admin;
        }else if(type.equals("user")){
            User user = userService.findByUsername(username);
            return user;
        }
        return null;
    }
}
