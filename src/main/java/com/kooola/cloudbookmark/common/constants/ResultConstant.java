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
    public static final String CBM_PARAM_MISSING = "1001";              //请确保必要参数不能为空


    public static final String CBM_HTML_GET_OR_PARSER_FAIL = "10001";       //抓取页面失败，书签无法生成

    public static final String CBM_USERNAME_PASSWD_EMPTY = "20000";  //用户名和密码不能为空
    public static final String CBM_USERNAME_NOT_EXIST = "20001"; //用户不存在
    public static final String CBM_PASSWORD_NOT_RIGHT = "20002"; //密码不正确
    public static final String CBM_USER_ALREADY_EXIST = "20003"; //用户已存在
    public static final String CBM_NOT_LOGIN = "20004";          //用户未登陆（权限控制：无权限）
    public static final String CBM_ACTIVE_PARAM_EMPTY_FAIL = "20005";   //参数无效，激活失败
    public static final String CBM_USER_ALEARDY_ACTIVE = "20006";       //用户已激活，无需再激活
    public static final String CBM_MAKE_ACTIVATION_PARAM_EMPTY_FAIL = "20007";   //参数无效，发起激活请求失败
    public static final String CBM_ACTIVE_TIME_OVER = "20008";    //激活码过期
    public static final String CBM_USER_NOT_ACTIVE = "20009";    //用户未激活
    public static final String CBM_SEND_MAIL_ACTIVE_FAIL = "20010";      //发送激活邮件失败
    public static final String CBM_OLD_NEW_PASSWD_SAME = "20011";       //新旧密码不能相同


    public static final String CBM_BOOKMARK_NOT_EXIST = "30001";        //该书签不存在
    public static final String CBM_CATALOG_NO_BOOKMARK = "30002";       //目录下无书签


    public static final Map<String, String> resultInfosMap = new HashMap();

    static {
        resultInfosMap.put(CBM_SUCCESS, "成功");
        resultInfosMap.put(CBM_FAIL, "失败");
        resultInfosMap.put(CBM_UNKNOWN_ERROR, "未知错误");
        resultInfosMap.put(CBM_PARAM_MISSING, "请确保必要参数不能为空");


        resultInfosMap.put(CBM_USERNAME_PASSWD_EMPTY, "用户名和密码不能为空");
        resultInfosMap.put(CBM_USERNAME_NOT_EXIST, "用户不存在");
        resultInfosMap.put(CBM_PASSWORD_NOT_RIGHT, "密码不正确");
        resultInfosMap.put(CBM_USER_ALREADY_EXIST, "用户已存在");
        resultInfosMap.put(CBM_NOT_LOGIN, "用户未登陆");
        resultInfosMap.put(CBM_HTML_GET_OR_PARSER_FAIL, "抓取页面失败，书签无法生存");
        resultInfosMap.put(CBM_ACTIVE_PARAM_EMPTY_FAIL, "参数无效，激活失败");
        resultInfosMap.put(CBM_USER_ALEARDY_ACTIVE, "用户已激活，无需再激活");
        resultInfosMap.put(CBM_MAKE_ACTIVATION_PARAM_EMPTY_FAIL, "参数无效，发起激活请求失败");
        resultInfosMap.put(CBM_ACTIVE_TIME_OVER, "激活码过期");
        resultInfosMap.put(CBM_USER_NOT_ACTIVE, "用户未激活");
        resultInfosMap.put(CBM_SEND_MAIL_ACTIVE_FAIL, "发送激活邮件失败");
        resultInfosMap.put(CBM_OLD_NEW_PASSWD_SAME, "新旧密码不能相同");

        resultInfosMap.put(CBM_BOOKMARK_NOT_EXIST, "该书签不存在");
        resultInfosMap.put(CBM_CATALOG_NO_BOOKMARK, "目录下无书签");
    }
}
