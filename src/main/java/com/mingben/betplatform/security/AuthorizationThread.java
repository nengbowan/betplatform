package com.mingben.betplatform.security;

import com.mingben.betplatform.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalTime;

/**
 *  //产品授权 thread daemon
 */
public class AuthorizationThread extends Thread {
    private Logger logger = LoggerFactory.getLogger(AuthorizationThread.class);
    @Override
    public void run() {
        //设置寄生线程
//        this.setDaemon(true);
        LocalTime start = LocalTime.now();
        boolean authed = false;
        //轮训检测文件是否存在
        if(new File("authorize").exists()){
            //已授权版本 直接线程退出
            authed = true;
            return;
        }
        logger.warn("当前版本是试用版本,,");
        //三十分钟试用
        while (true){
            LocalTime end = LocalTime.now();
            //快要退出的时候 警示
            if(end.toSecondOfDay() - start.toSecondOfDay() > 20 * 60){
                logger.warn("当前版本是试用版本,,还可使用10分钟,程序即将退出");
            }
            //退出程序
            if(end.toSecondOfDay() - start.toSecondOfDay() > 30  * 60){
                System.exit(0);
            }
            //防止浪费不必要的性能消耗
            ThreadUtil.sleep10M();
        }
    }
}
