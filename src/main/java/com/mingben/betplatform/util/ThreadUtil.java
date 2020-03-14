package com.mingben.betplatform.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadUtil {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.class);
    /**
     * 休眠三十秒
     */
    public static void sleep30s() {
        logger.info("等候三十秒..");
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**
     * 休眠三秒
     */
    public static void sleep3s() {
        logger.info("等候三秒..");
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 休眠5秒
     */
    public static void sleep5s() {
        logger.info("等候三秒..");
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 休眠10秒
     */
    public static void sleep10s() {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 休眠10分钟
     */
    public static void sleep10M() {
        try {
            Thread.sleep(10 * 6 * 10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
