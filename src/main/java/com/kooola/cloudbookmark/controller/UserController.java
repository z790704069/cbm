package com.kooola.cloudbookmark.controller;

import com.kooola.cloudbookmark.common.RestResponseModel;
import com.kooola.cloudbookmark.common.UserThreadLocal;
import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.common.constants.WebConst;
import com.kooola.cloudbookmark.domain.User;
import com.kooola.cloudbookmark.service.UserService;
import com.kooola.cloudbookmark.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by march on 2018/7/26.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param email   登录邮箱
     * @param password 密码
     * @param remeber_me
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "login")
    @ResponseBody
    @CrossOrigin("*")
    public RestResponseModel doLogin(@RequestParam String email, @RequestParam String password,
                                     @RequestParam(required = false) String remeber_me,
                                     HttpServletRequest request, HttpServletResponse response){
        User user = null;
        try{
            user = userService.login(email, password);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            if(StringUtils.isNotBlank(remeber_me) && remeber_me.equals("true")){
                HttpUtil.setCookie(response, user.getUid());
            }
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }
        return new RestResponseModel(ResultConstant.CBM_SUCCESS, user);
    }

    /**
     * 用户登出
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "logout")
    @ResponseBody
    public RestResponseModel doLogout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new RestResponseModel(ResultConstant.CBM_SUCCESS);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping(value = "register")
    @ResponseBody
    public RestResponseModel doRegister(User user){
        try{
            userService.register(user);
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }

        return new RestResponseModel(ResultConstant.CBM_SUCCESS);
    }


    /**
     * 用户修改密码
     * @param oldPasswd  旧密码
     * @param newPasswd  新密码
     * @return
     */
    @PostMapping(value = "/user/chagepasswd")
    @ResponseBody
    public RestResponseModel changePasswd(@RequestParam String oldPasswd, @RequestParam String newPasswd){
        if(StringUtils.isBlank(newPasswd) || StringUtils.isBlank(oldPasswd)){
            return new RestResponseModel(ResultConstant.CBM_PARAM_MISSING);   //必要参数不能为空
        }
        if(newPasswd.equals(oldPasswd)){
            return new RestResponseModel(ResultConstant.CBM_OLD_NEW_PASSWD_SAME);  //新旧密码不能相同
        }
        User user = UserThreadLocal.getUser();
        try{
            userService.changePasswd(user, oldPasswd, newPasswd);
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }
        return new RestResponseModel(ResultConstant.CBM_SUCCESS);
    }




    /**
     * 激活用户,用户拿自己邮箱中url进行请求
     * @param email 邮箱
     * @param code 激活码
     * @return
     */
    @GetMapping(value = "/user/active")
    @ResponseBody
    public RestResponseModel doActive(@RequestParam String email, @RequestParam String code){
        if(StringUtils.isBlank(email) || StringUtils.isBlank(code)){
            return new RestResponseModel(ResultConstant.CBM_ACTIVE_PARAM_EMPTY_FAIL); //参数为空，激活失败
        }
        try{
            userService.active(email, code);
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }
        return new RestResponseModel(ResultConstant.CBM_SUCCESS);
    }

    /**
     * 发起激活申请
     * @param email
     * @return
     */
    @PostMapping(value = "/user/makeActivation")
    @ResponseBody
    public RestResponseModel makeActivation(@RequestParam String email){
        if(StringUtils.isBlank(email)){
            return new RestResponseModel(ResultConstant.CBM_MAKE_ACTIVATION_PARAM_EMPTY_FAIL);  //参数无效，发起激活请求失败
        }
        try{
            userService.makeActivation(email);
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }
        return new RestResponseModel(ResultConstant.CBM_SUCCESS);
    }
}
