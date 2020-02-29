package com.mingben.betplatform.api.pk6758;

import com.alibaba.fastjson.JSONObject;
import com.mingben.betplatform.dto.pk6758.OpenHistory;
import com.mingben.betplatform.exception.*;
import com.mingben.betplatform.util.ArrayUtil;
import com.mingben.betplatform.util.LogUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * official url : http://www.pk6758.com
 * 包含登錄 下注接口
 */
public class Pk6758Api {
    private String username;
    private String password;
    //http client cookie
    private CookieStore cookieStore = new BasicCookieStore();
    //http client
    private HttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

    private String uuid;
    public Pk6758Api(String username , String password ){
        this.username = username;
        this.password = password;
    }

    public void login(){
        //登錄前 獲取綁定的session
        try {
            retriverSession();
            //將session綁定到當前登錄人
            doLogin();
            //獲取 uid 請求開獎結果需要
            retriveUUid();
            //獲取開獎結果
            OpenHistory openHistory = retriveOpenHistory();
            //下注
            bet(openHistory);
        } catch (SessionRetriveException e) {
            e.printStackTrace();
        } catch (OpenHistoryRetriveException e) {
            e.printStackTrace();
        } catch (UuidRetriveException e) {
            e.printStackTrace();
        } catch (LoginFailedException e) {
            e.printStackTrace();
        } catch (BetFailureException e) {
            e.printStackTrace();
        }

    }

    /**
     * 下注
     */
    private void bet(OpenHistory openHistory ) throws BetFailureException {
        //看開獎結果1在哪位  從0開始算
        int index = whereOneIn(openHistory);
        String sortAry = BetStringFactory.getBetString(String.valueOf(index));
        String url = "http://www.pk6758.com/Member/DownEntry.aspx?t="+uuid;
        HttpPost betPost = new HttpPost(url);
        betPost.setHeader("Content-Type" , "application/x-www-form-urlencoded");
        betPost.setHeader("User-Agent" , " Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0");
        betPost.setHeader("Referer" , " http://www.pk6758.com/Member/LoginValidate.aspx");
        //cookie client自動管理
        NameValuePair gamePair = new BasicNameValuePair("gameIndex" , "8");
        NameValuePair numberPair = new BasicNameValuePair("number" , "20200229060");
        NameValuePair sortAryPair = new BasicNameValuePair("sortAry" , sortAry);
        NameValuePair typePair = new BasicNameValuePair("dataType" , "2");
        List<NameValuePair>  valuePairs = Arrays.asList(gamePair , numberPair , sortAryPair , typePair);
        try {
            betPost.setEntity(new UrlEncodedFormEntity(valuePairs , "utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = client.execute(betPost);
            String respStr = EntityUtils.toString(response.getEntity());
            if(!respStr.contains("usableCredit")){
                LogUtil.requestLogger("下注失敗 ," , url ,  valuePairs.toString() , respStr );
                throw new BetFailureException("下注失敗");
            }
        } catch (IOException e) {
            throw new BetFailureException("下注失敗");
        }
    }
    //
    private int whereOneIn(OpenHistory openHistory) {
        List<Integer> numbers = openHistory.getOpenNumList().getNumList();
        for(int index = 0 ; index < numbers.size() ; index ++ ){
            Integer number = numbers.get(index);
            if(number.intValue() == 1){
                return index;
            }
        }
        return -1;
    }

    /**
     * 獲取開獎結果
     */
    private OpenHistory retriveOpenHistory() throws OpenHistoryRetriveException {

        String url = "http://www.pk6758.com/Member/Game_KC.aspx";
        HttpPost historyPost = new HttpPost(url);
        historyPost.setHeader("Content-Type" , "application/x-www-form-urlencoded");
        historyPost.setHeader("User-Agent" , " Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0");
        historyPost.setHeader("Referer" , " http://www.pk6758.com/Member/LoginValidate.aspx");
        //cookie client自動管理
        NameValuePair uuidPair = new BasicNameValuePair("t" , uuid);
        NameValuePair gamePair = new BasicNameValuePair("gameIndex" , "8");
        NameValuePair typePair = new BasicNameValuePair("type" , "1");
        List<NameValuePair>  valuePairs = Arrays.asList(uuidPair , gamePair , typePair);
        try {
            historyPost.setEntity(new UrlEncodedFormEntity(valuePairs , "utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = client.execute(historyPost);
            String respStr = EntityUtils.toString(response.getEntity());
            if(!respStr.contains("win")){
                LogUtil.requestLogger("獲取開獎結果 ," , url ,  valuePairs.toString() , respStr );
                throw new OpenHistoryRetriveException();
            }
            OpenHistory openHistory =  JSONObject.parseObject(respStr , OpenHistory.class);
            return openHistory;
        } catch (IOException e) {
            throw new OpenHistoryRetriveException();
        }
    }

    /**
     * html頁面找到uuid
     * @param content
     * @return
     */
    public String getUuid(String content){
        String canZhaoWu = "autoTid: \"";
        int start = content.indexOf(canZhaoWu) + canZhaoWu.length();
        int end = start + 32;
        return content.substring( start , end);
    }
    /**
     * 獲取uuid
     */
    private void retriveUUid() throws UuidRetriveException {
        String url = "http://www.pk6758.com/Member/";
        HttpGet uuidGet = new HttpGet(url);
        uuidGet.setHeader("Content-Type" , "application/x-www-form-urlencoded");
        uuidGet.setHeader("User-Agent" , " Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0");
        uuidGet.setHeader("Referer" , " http://www.pk6758.com/Member/LoginValidate.aspx");
        try {
            HttpResponse response = client.execute(uuidGet);
            String respStr = EntityUtils.toString(response.getEntity());
            String uuid = getUuid(respStr);
            if("".equals(uuid)){
                LogUtil.requestLogger("獲取uuid失敗 ,," , url , "" , respStr);
                throw new UuidRetriveException();
            }
            this.uuid = uuid;
        } catch (IOException e) {
            throw new UuidRetriveException();
        }

    }

    /**
     * 登錄
     */
    private void doLogin() throws LoginFailedException {
        String url = "http://www.pk6758.com/Member/Login/Default.aspx";
        HttpPost loginPost = new HttpPost(url);
        loginPost.setHeader("Content-Type" , "application/x-www-form-urlencoded");
        //cookie client自動管理
        //loginPost.setHeader("Cookie" , "ASP.NET_SessionId=5i4x1raah5x10cz3tdyks02a");
        NameValuePair namePair = new BasicNameValuePair("loginName" , username);
        NameValuePair passPair = new BasicNameValuePair("loginPwd" , password);
        List<NameValuePair>  valuePairs = Arrays.asList(namePair , passPair);
        try {
            loginPost.setEntity(new UrlEncodedFormEntity(valuePairs , "utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = client.execute(loginPost);
            String respStr = EntityUtils.toString(response.getEntity());
            if(!"1".equals(respStr)){
                LogUtil.requestLogger("登陸失敗 ," , url ,  valuePairs.toString() , respStr );
                throw new LoginFailedException();
            }
        } catch (IOException e) {
            throw new LoginFailedException();
        }
    }

    /**
     * 獲取session
     * @throws SessionRetriveException
     */
    private void retriverSession() throws SessionRetriveException {
        String url = "http://www.pk6758.com/";
        HttpGet sessionGet = new HttpGet(url);
        sessionGet.setHeader("Content-Type" , "application/x-www-form-urlencoded");
        try {
            HttpResponse response = client.execute(sessionGet);
            //校驗是否返回頭包含 Name = ASP.NET_SessionId

            List<Cookie> cookies = cookieStore.getCookies();
            for(Cookie c : cookies){
                if("ASP.NET_SessionId".equals(c.getName())){
                    if(c.getValue() == null){
                        throw new SessionRetriveException();
                    }
                    return;
                }
            }
            throw new SessionRetriveException();

        } catch (IOException e) {
            throw new SessionRetriveException();
        }
    }

}
