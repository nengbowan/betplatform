package com.mingben.betplatform.exception;

public class UserPasswordNotMatchException extends Exception{
    public UserPasswordNotMatchException(){
        super("用户名密码不匹配");
    }
}
