package com.mingben.betplatform.api;

public class UUidGetTests {
    public static void main(String[] args) {
        String content = "    function sysInfo() {\n" +
                "        return {\n" +
                "            autoTid: \"96488a3074064d4b4aae15e20dbfa47d\",\n" +
                "            first:0,\n" +
                "            animalsIndex:1,\n" +
                "            maxPayout:5000000,\n" +
                "            detailsBak:1,\n" +
                "            autoOdds:[0.7598,0.8346],\n" +
                "            wx:{\"1\":[37,36,21,20,7,6,17,16,39,38],\"2\":[1,49,48,11,10,33,32,19,18,29,28],\"3\":[25,24,47,46,9,8,5,4,27,26],\"4\":[13,12,35,34,43,42,15,14],\"5\":[23,22,45,44,31,30,41,40,3,2]},\n" +
                "            lastData:[],\n" +
                "            voice: 1";
        System.out.println(getUuid(content));
    }
    public static String getUuid(String content){
        String canZhaoWu = "autoTid: \"";
        int start = content.indexOf(canZhaoWu) + canZhaoWu.length();
        int end = start + 32;
        return content.substring( start , end);
    }
}
