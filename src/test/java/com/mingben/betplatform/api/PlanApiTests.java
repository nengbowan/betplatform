package com.mingben.betplatform.api;

import java.util.Map;

public class PlanApiTests {
    public static void main(String[] args) {
        Map<String,Object> map =  PlanApi.getPlanBetMap();
        System.out.println(map);
    }
}
