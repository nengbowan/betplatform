package com.mingben.betplatform;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.OptionHelper;
import org.slf4j.LoggerFactory;

public class Log4j2Tests {
    public static void main(String[] args) {

        LoggerFactory.getLogger(Log4j2Tests.class).error("ssssadfad");
    }
}
