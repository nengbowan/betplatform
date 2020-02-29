package com.mingben.betplatform.exception;

public class LoginFailedException extends Exception {
    public LoginFailedException(){
        super("登錄失敗 ...");
    }
}
