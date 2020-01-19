package com.mingben.betplatform.api;

import com.alibaba.fastjson.JSONObject;
import com.mingben.betplatform.dto.plan.Cplan;
import com.mingben.betplatform.dto.plan.PlanDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方大发快三计划api
 */
public class PlanApi {
    private static Logger logger = LoggerFactory.getLogger(PlanApi.class);

    /**
     * 获取计划
     * @return
     */
    public static PlanDto getPlan(){
        //构造get请求
        String url = "https://250026.com/plan/api.do?code=dfk3&plan=0&size=20&planSize=20&_t=%s";
        long currentTimeStamp = System.currentTimeMillis();
        String requestUrl = String.format(url , currentTimeStamp);

        try {
            URL planUrl = new URL(requestUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)planUrl.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            InputStream in = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = "";
            StringBuffer content = new StringBuffer();
            while((line = br.readLine()) != null){
                content.append(line);
            }
            if(!content.toString().contains("{\"code\":1")){
                logger.error("请求计划api出错,"+content.toString());
                return null;
            }
            return JSONObject.parseObject(content.toString() , PlanDto.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取下注大还是小 key betWhere,下注几倍 betBei , 下注期数 betNum
     */
    public static Map<String,Object> getPlanBetMap (){
        Map<String,Object> betMap = new HashMap<>();
        PlanDto planDto = getPlan();
        Cplan cplan = planDto.getData().getCplan();
        betMap.put("betWhere",cplan.getPlans().get(0));
        //下注倍数数组
        int [] beiArray = new int []{1,3,8,24};
        long betIndex = Long.valueOf(cplan.getWinIssue()) - Long.valueOf(cplan.getBeginIssue());
        betMap.put("betBei", beiArray[(int)betIndex]);
        betMap.put("betNum", Long.valueOf(cplan.getWinIssue()));
        return betMap;
    }

}
