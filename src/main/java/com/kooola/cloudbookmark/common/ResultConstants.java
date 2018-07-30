package com.kooola.cloudbookmark.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by march on 2018/7/26.
 */
public class ResultConstants {
    public static final String SUCCESS = "0"; //成功
    public static final String FAIL = "-1"; //失败


    public static final String CBM_USERNAME_PASSWD_EMPTY = "20000";  //用户名和密码不能为空
    public static final String CBM_USERNAME_NOT_EXIST = "20001"; //用户不存在
    public static final String CBM_PASSWORD_NOT_RIGHT = "20002"; //密码不正确
    public static final String CBM_USER_ALREADY_EXIST = "20003"; //用户已存在


    public static final Map<String, String> resultInfosMap = new HashMap();

    static {
        resultInfosMap.put(SUCCESS, "成功");
        resultInfosMap.put(FAIL, "失败");
        resultInfosMap.put(CBM_USERNAME_PASSWD_EMPTY, "用户名和密码不能为空");
        resultInfosMap.put(CBM_USERNAME_NOT_EXIST, "用户不存在");
        resultInfosMap.put(CBM_PASSWORD_NOT_RIGHT, "密码不正确");
        resultInfosMap.put(CBM_USER_ALREADY_EXIST, "用户已存在");
    }
}
