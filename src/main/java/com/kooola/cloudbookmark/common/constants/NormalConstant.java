package com.kooola.cloudbookmark.common.constants;

/**
 * Created by march on 2018/7/26.
 */
public class NormalConstant {
    public static final int SALT_LENGTH = 8;   //密码盐的长度

    public static final String CBM_PAGE_LIMIT_SIZE = "6";   //书签分页默认长度

    public static final String CBM_MAIL_NICKNAME = "云书签";  //发件箱账户昵称

    public static final String CBM_MAIL_STMP_ADDRESS = "smtp.163.com";  //邮箱stmp服务地址

    public static final String CBM_MAIL_ACCOUNT = "zwq20110815@163.com"; //云书签发件人账号

    public static final String CBM_MAIL_ACCOUNT_PASSWD = "******"; //云书签发件人账号的密码

    public static final Long CBM_ACTIVE_DURING_TIME = 300l;    //获取激活链接到实际激活，最长不能超过的时间

    public static final String CBM_MAKE_ACTIVATION_URL = "http://localhost:8080/user/active";
}
