package com.mingben.betplatform.util;

public class ArrayUtil {
    public static boolean isNotEmpty(Object [] arr){
        return arr != null && arr.length > 0 ;
    }

    public static boolean isEmpty(Object [] arr){
        return !isNotEmpty(arr);
    }
}
