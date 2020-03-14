package com.mingben.betplatform.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class JwtTokenUtil {

    private static String secret = "a123";
    /**
     *
     * @param username
     * @param passwd
     * @param type admin user
     * @return
     */
    public static String createToken(String username, String passwd , String type){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withClaim("username",username)
                    .withClaim("password",passwd)
                    .withClaim("type" , type)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param token
     */
    public static HashMap<String,String> getPayLoad(String token)throws JWTVerificationException{
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            String username = jwt.getClaim("username").asString();
            String password = jwt.getClaim("password").asString();
            String type = jwt.getClaim("type").asString();
            HashMap<String,String> jwtMap = new HashMap();
            jwtMap.put("username",username);
            jwtMap.put("password",password);
            jwtMap.put("type",type);
            return jwtMap;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){
        HashMap<String,String> payload = getPayLoad( token);
        return payload.get("username");
    }

    /**
     * 获取密码
     * @param token
     * @return
     */
    public static String getPassword(String token){
        HashMap<String,String> payload = getPayLoad( token);
        return payload.get("password");
    }


}
