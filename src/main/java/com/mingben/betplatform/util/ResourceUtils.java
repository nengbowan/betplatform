package com.mingben.betplatform.util;

/**
 * 资源工具类
 */
public class ResourceUtils {
    public static boolean isStaticResource(String resourceUrl){
        boolean isStatic = resourceUrl.contains(".css") || resourceUrl.contains(".js") || resourceUrl.contains(".png")|| resourceUrl.contains(".jpg")
                || resourceUrl.contains(".ico") || resourceUrl.contains(".woff") || resourceUrl.contains(".ttf");
        return isStatic || resourceUrl.contains(".html");
    }
}
