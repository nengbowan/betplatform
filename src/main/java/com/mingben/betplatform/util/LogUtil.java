package com.mingben.betplatform.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);
    /**
     * 日志打印　请求url , 请求实体 ,返回内容
     *
     * @param requestUrl
     * @param requestBody
     * @param resp
     */
    public static void requestLogger(String title, String requestUrl, String requestBody, String resp) {
        logger.info("--------------------------------------------------------------");
        logger.info("----------------------" + title + "------------------------------");
        logger.info(String.format("------------------URL:-----------%s------------------------------", requestUrl));
        logger.info(String.format("-----------------BODY:-----------%s---------------------------", requestBody));
        logger.info(String.format("-----------------RESP:-----------%s----------------------------", resp));
        logger.info("--------------------------------------------------------------");
    }
}
