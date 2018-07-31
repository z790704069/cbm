package com.kooola.cloudbookmark.utils;

/**
 * Created by march on 2018/7/30.
 * 数字 字符串工具类
 */
public class DigitStringUtil {

    /**
     * 判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        if(null != str && 0 != str.trim().length() && str.matches("\\d*")){
            return true;
        }
        return false;
    }
}
