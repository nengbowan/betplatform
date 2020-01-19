package com.mingben.betplatform.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    /**
     * hour must be 大于 0
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date , double hour){
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(date);
        if(hour == 0.5d ){
            cal.add(Calendar.MINUTE , 30);
        }else{
            cal.add(Calendar.HOUR , (int)hour);
        }
        return cal.getTime();
    }
}
