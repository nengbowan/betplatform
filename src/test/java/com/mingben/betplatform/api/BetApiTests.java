package com.mingben.betplatform.api;

import com.mingben.betplatform.dto.plan.PlanDto;
import com.mingben.betplatform.exception.BetLoginException;

import java.io.IOException;

public class BetApiTests {
    public static void main(String[] args) throws IOException, BetLoginException {
        BetApi api = new BetApi("bcceshi","qweasdqwe");
        api.login();

        PlanDto planDto = PlanApi.getPlan();
        if(planDto != null){
//            planDto.getData()
        }
    }
}
