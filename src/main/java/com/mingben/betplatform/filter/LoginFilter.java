package com.mingben.betplatform.filter;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mingben.betplatform.dto.ResultDto;
import com.mingben.betplatform.service.AdminService;
import com.mingben.betplatform.service.UserService;
import com.mingben.betplatform.util.AccessControlUtils;
import com.mingben.betplatform.util.JwtTokenUtil;
import com.mingben.betplatform.util.ResourceUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class LoginFilter implements Filter {

    //用户登录url
    private static String USER_LOGIN_URL = "/user/login";
    //后台登录url
    private static String ADMIN_LOGIN_URL = "/admin/login";
    private static List<String> whiteUrlList = Arrays.asList(USER_LOGIN_URL ,ADMIN_LOGIN_URL,"/admin/index");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //跨站
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest reqs = (HttpServletRequest) request;
        if(request instanceof HttpServletRequest){
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String requestUrl = httpRequest.getRequestURI().toString();


            //是否认证成功
            boolean authed = false;
            boolean isUser = true;
            //白名单　或者　静态前端文件　不走权限认证
            if(whiteUrlList.contains(requestUrl) || ResourceUtils.isStaticResource(requestUrl)){
                authed = true;
            }else{
                String jwtToken = ((HttpServletRequest) request).getHeader("token");
                //未授权
                if(StringUtils.isNotEmpty(jwtToken)){
                    //验证
                    String token = reqs.getHeader("token");

                    Map<String,String> decodeMap = null;
                    try {
                        decodeMap = JwtTokenUtil.getPayLoad(token);
                    }catch (JWTVerificationException e){
                        //hold on
                    }

//                    String username = decodeMap.get("username");
//                    String passwd = decodeMap.get("password");
                    String type = decodeMap.get("type");

                    if (type.equals("admin")) {
                        isUser = false;
                    }

                    if(decodeMap != null){
                       authed = true;
                    }
                }
            }

            if(authed){
                //跨域工具类
                AccessControlUtils.accessAllowAll(response);
                chain.doFilter(request , response);
            }else{
                if(isUser){
                    //重定向到用户登录
                    response.sendRedirect(USER_LOGIN_URL);
                }else{
                    //重定向到后台登录
                    response.sendRedirect(ADMIN_LOGIN_URL);
                }
            }
        }

    }

    @Override
    public void destroy() {

    }
}
