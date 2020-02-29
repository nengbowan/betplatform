package com.mingben.betplatform.api.pk6758;

import java.util.HashMap;
import java.util.Map;

public class BetStringFactory {
    private static Map<String,String> betStringMap = new HashMap<String,String>();
    //加載靜態冠軍 亞軍字典表
    static{
        betStringMap.put("冠军" , "1:31:1,1:28:1,1:25:1,1:24:1");
        betStringMap.put("亚军" , "1:47:1,1:44:1,1:41:1,1:40:1");
        betStringMap.put("第三名" , "1:63:1,1:60:1,1:57:1,1:56:1");
        betStringMap.put("第四名" , "1:79:1,1:76:1,1:73:1,1:72:1");
        betStringMap.put("第五名" , "1:95:1,1:92:1,1:89:1,1:88:1");
        betStringMap.put("第六名" , "1:111:1,1:108:1,1:105:1,1:104:1");
        betStringMap.put("第七名" , "1:127:1,1:124:1,1:121:1,1:120:1");
        betStringMap.put("第八名" , "1:143:1,1:140:1,1:137:1,1:136:1");
        betStringMap.put("第九名" , "1:159:1,1:156:1,1:153:1,1:152:1");
        betStringMap.put("第十名" , "1:175:1,1:172:1,1:169:1,1:168:1");
    }
    // 獲取投注策略
    public static String getBetString(String index){
        StringBuilder result = new StringBuilder();

        switch (index){
            //投注 冠軍亞軍 3 4 7 10
            case "0" : ;
            case "1" : result.append(betStringMap.get("冠军"));result.append(",");result.append(betStringMap.get("第三名"));break;
            //投注 冠軍亞軍 3 4 7 10
            case "2" : result.append(betStringMap.get("亚军"));result.append(",");result.append(betStringMap.get("第四名"));break;
            case "3" : result.append(betStringMap.get("第三名"));result.append(",");result.append(betStringMap.get("第五名"));break;
            case "4" : result.append(betStringMap.get("第四名"));result.append(",");result.append(betStringMap.get("第六名"));break;
            case "5" : result.append(betStringMap.get("第五名"));result.append(",");result.append(betStringMap.get("第七名"));break;
            case "6" : result.append(betStringMap.get("第六名"));result.append(",");result.append(betStringMap.get("第八名"));break;
            case "7" : result.append(betStringMap.get("第七名"));result.append(",");result.append(betStringMap.get("第九名"));break;
            case "8" : result.append(betStringMap.get("第八名"));result.append(",");result.append(betStringMap.get("第十名"));break;
            case "9" : result.append(betStringMap.get("第九名"));result.append(",");result.append(betStringMap.get("第十名"));break;
            default: break;

        }
        return result.toString();
    }
}
