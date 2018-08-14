package com.kooola.cloudbookmark.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by march on 2018/7/26.
 */
public class ResultConstant {
    public static final String CBM_SUCCESS = "0"; //成功
    public static final String CBM_FAIL = "-1"; //失败
    public static final String CBM_UNKNOWN_ERROR = "1000";           //未知错误


    public static final String CBM_HTML_GET_OR_PARSER_FAIL = "10001";       //抓取页面失败，书签无法生成

    public static final String CBM_USERNAME_PASSWD_EMPTY = "20000";  //用户名和密码不能为空
    public static final String CBM_USERNAME_NOT_EXIST = "20001"; //用户不存在
    public static final String CBM_PASSWORD_NOT_RIGHT = "20002"; //密码不正确
    public static final String CBM_USER_ALREADY_EXIST = "20003"; //用户已存在
    public static final String CBM_NOT_LOGIN = "20004";          //用户未登陆（权限控制：无权限）


    public static final String CBM_BOOKMARK_NOT_EXIST = "30001";        //该书签不存在
    public static final String CBM_CATALOG_NO_BOOKMARK = "30002";       //目录下无书签


    public static final Map<String, String> resultInfosMap = new HashMap();

    static {
        resultInfosMap.put(CBM_SUCCESS, "成功");
        resultInfosMap.put(CBM_FAIL, "失败");
        resultInfosMap.put(CBM_UNKNOWN_ERROR, "未知错误");
        resultInfosMap.put(CBM_USERNAME_PASSWD_EMPTY, "用户名和密码不能为空");
        resultInfosMap.put(CBM_USERNAME_NOT_EXIST, "用户不存在");
        resultInfosMap.put(CBM_PASSWORD_NOT_RIGHT, "密码不正确");
        resultInfosMap.put(CBM_USER_ALREADY_EXIST, "用户已存在");
        resultInfosMap.put(CBM_NOT_LOGIN, "用户未登陆");
        resultInfosMap.put(CBM_HTML_GET_OR_PARSER_FAIL, "抓取页面失败，书签无法生存");
        resultInfosMap.put(CBM_BOOKMARK_NOT_EXIST, "该书签不存在");
        resultInfosMap.put(CBM_CATALOG_NO_BOOKMARK, "目录下无书签");
    }
}
