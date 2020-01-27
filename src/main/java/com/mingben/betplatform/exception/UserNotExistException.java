package com.mingben.betplatform.exception;

public class UserNotExistException extends Exception{
    public UserNotExistException(){
        super("用户不存在");
    }
}
