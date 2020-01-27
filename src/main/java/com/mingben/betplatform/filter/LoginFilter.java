package com.mingben.betplatform.filter;

import com.alibaba.fastjson.JSONObject;
import com.mingben.betplatform.dto.ResultDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class LoginFilter implements Filter {


    private List<String> whiteUrlList = Arrays.asList("/user/login","/admin/login");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest){
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String requestUrl = httpRequest.getRequestURI().toString();
            if(whiteUrlList.contains(requestUrl)){
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
