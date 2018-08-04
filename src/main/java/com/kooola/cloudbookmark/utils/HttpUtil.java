package com.kooola.cloudbookmark.utils;

import com.alibaba.fastjson.JSON;
import com.kooola.cloudbookmark.common.constants.WebConst;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by march on 2018/7/30.
 * IP工具类
 */
public class HttpUtil {

    /**
     *
     * @param request
     * @return IP
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     *
     * @param request
     * @return
     */
    public static Object getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(null == session){
            return null;
        }
        return session.getAttribute(WebConst.LOGIN_SESSION_KEY);
    }

    /**
     * 从cookie中获取uid
     * @param request
     * @return
     */
    public static Integer getUidFromCookie(HttpServletRequest request){
        if(null == request){
            return null;
        }
        Cookie cookie = getCookieByName(request, WebConst.USER_IN_COOKIE);
        if(null == cookie || null == cookie.getValue()){
            return null;
        }
        try{
            String uid = SecurityUtil.decryptAes(cookie.getValue(), WebConst.AES_SALT);
            return StringUtils.isNotBlank(uid) && DigitStringUtil.isNumber(uid) ? Integer.valueOf(uid) : null;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 通过名称获取cookie
     * @param request
     * @param name
     * @return
     */
    private static Cookie getCookieByName(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(null == cookies){
            return null;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }
        return null;
    }

    /**
     * 设置记住cookie
     *
     * @param response
     * @param uid
     */
    public static void setCookie(HttpServletResponse response, Long uid) {
        try {
            String val = SecurityUtil.encryptAes(uid.toString(), WebConst.AES_SALT);
            boolean isSSL = false;
            Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, val);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);
            cookie.setSecure(isSSL);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void outputObject(HttpServletResponse response, Object pm){
        PrintWriter pw = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            pw = response.getWriter();
            pw.write(JSON.toJSONString(pm));
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (pw!=null){
                pw.close();
            }
        }
    }

}
