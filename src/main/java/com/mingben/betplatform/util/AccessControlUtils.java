package com.mingben.betplatform.util;

import javax.servlet.http.HttpServletResponse;

/**
 * 跨域工具类
 */
public class AccessControlUtils {
    public static void accessAllowAll(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "true");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    }
}
