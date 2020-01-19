package com.mingben.betplatform.api;

import com.alibaba.fastjson.JSONObject;
import com.mingben.betplatform.dto.bet.BetRequestDto;
import com.mingben.betplatform.exception.BetFailureException;
import com.mingben.betplatform.exception.BetLoginException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 投注网站api
 */
public class BetApi {
    private Logger logger = LoggerFactory.getLogger(BetApi.class);
    private String username;
    private String passwd;

    private CookieStore cookieStore = new BasicCookieStore();

    private HttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

    public BetApi(String username ,String passwd){
        this.username = username;
        this.passwd = passwd;
    }
    public void login()throws IOException, BetLoginException {
        String url = "https://caishengoucai8.com:8943/v1/users/login";
        HttpPost post = new HttpPost(url);
        String requestContent = "userName=bcceshi&password=31ee24aa82aa5328ae85928c12f09152&version=1&random=ZGFmYWNsb3VkXzAuMjc4MzcxODE3ODc0NTMzMzY%3D";
        HttpEntity entity = new StringEntity(requestContent, ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8));
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        String resp = EntityUtils.toString(response.getEntity() , Charset.forName("utf8"));
        if(!resp.contains("登录成功")){
            String message = "登录失败,"+resp;
            logger.error(message);
            throw new BetLoginException(message);
        }
        boolean found = false;
        //检测是否有token键值对
        List<Cookie> cookieList = cookieStore.getCookies();
        if(!CollectionUtils.isEmpty(cookieList)){
            for(Cookie cookie : cookieList ){
                String name = cookie.getName();
                if("JSESSIONID".equals(name)){
                    found = true;
                }

            }
        }
        if(found == false){
            String message = "登陆失败,token不存在";
            logger.error(message);
            throw new BetLoginException(message);
        }
    }


    /**
     * 下注大发一分彩
     * @param lotteryCode 下注彩票类型　大发一份彩
     * @param lotteryDetail  下注类型　大　还是　小的编码
     * @param number 下注类型　大　还是　小
     * @param issue　下注期数
     * @param amount　下注金额
     */
    public void bet(String lotteryCode , String lotteryDetail ,String number,  String issue , int amount)throws IOException,BetFailureException{
        String url = "https://caishengoucai8.com:8943/v1/betting/addBetting";
        HttpPost post = new HttpPost(url);
        BetRequestDto requestDto = BetRequestDto.create(lotteryCode , lotteryDetail , number , issue , amount );
        String requestContent = "bettingData=%5B"+ JSONObject.toJSONString(requestDto) +"%5D";
        HttpEntity entity = new StringEntity(requestContent, ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8));
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        String resp = EntityUtils.toString(response.getEntity() , Charset.forName("utf8"));
        if(!resp.contains("成功")){
            String message = "下注失败,"+resp;
            logger.error(message);
            throw new BetFailureException(message);
        }
    }

    /**
     * 下注大发一份彩　和值　大
     * @param issue
     * @param amount
     * @throws IOException
     * @throws BetFailureException
     */
    public void betDa( String issue , int amount) throws IOException, BetFailureException {
        bet("1407", "1407A10","大" , issue, amount);
    }

    /**
     * 下注大发一份彩　和值　小
     * @param issue
     * @param amount
     * @throws IOException
     * @throws BetFailureException
     */
    public void betXiao( String issue , int amount) throws IOException, BetFailureException {
        bet("1407", "1407A10","小" , issue, amount);
    }

    /**
     * 下注大发一份彩　和值　单
     * @param issue
     * @param amount
     * @throws IOException
     * @throws BetFailureException
     */
    public void betDan( String issue , int amount) throws IOException, BetFailureException {
        bet("1407", "1407A10","单" , issue, amount);
    }

    /**
     * 下注大发一份彩　和值　双
     * @param issue
     * @param amount
     * @throws IOException
     * @throws BetFailureException
     */
    public void betShuang( String issue , int amount) throws IOException, BetFailureException {
        bet("1407", "1407A10", "双", issue, amount);
    }

    /**
     * 获取开奖历史　从计划网获取
     */
    @Deprecated
    public void getOpenHistory(){

    }
}
