package com.mingben.betplatform.filter;

import com.alibaba.fastjson.JSONObject;
import com.mingben.betplatform.dto.ResultDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class LoginFilter implements Filter {


    private static List<String> whiteUrlList = Arrays.asList("/user/login","/admin/login","/admin/index");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest){
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String requestUrl = httpRequest.getRequestURI().toString();

            //跨站
            HttpServletResponse response = (HttpServletResponse) res;
            HttpServletRequest reqs = (HttpServletRequest) request;
            String curOrigin = reqs.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", curOrigin == null ? "true" : curOrigin);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            if(whiteUrlList.contains(requestUrl) || requestUrl.contains(".css") || requestUrl.contains(".js") || requestUrl.contains(".png")|| requestUrl.contains(".jpg") 
            		|| requestUrl.contains(".ico") || requestUrl.contains(".woff") || requestUrl.contains(".ttf")){
                chain.doFilter(request , response);
            }else{
                String jwtToken = ((HttpServletRequest) request).getHeader("token");
                //未授权
                if(StringUtils.isEmpty(jwtToken)){
                    PrintWriter pw = response.getWriter();
                    ResultDto result = ResultDto.builder().success(false).message("403").build();
                    pw.println(JSONObject.toJSONString(result));
                    return;
                    
                }
                chain.doFilter(request , response);

                //jwtToken 有效性验证 TODO RoleAnnotationParameterResolver
            }
        }
    }

    @Override
    public void destroy() {

    }
}
