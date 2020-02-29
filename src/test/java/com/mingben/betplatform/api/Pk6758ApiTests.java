package com.mingben.betplatform.api;

import com.mingben.betplatform.api.pk6758.Pk6758Api;

public class Pk6758ApiTests {
    public static void main(String[] args) {
        String username ="cao222";
        String password = "qwer1234";
        new Pk6758Api(username , password ).login();
    }
}
