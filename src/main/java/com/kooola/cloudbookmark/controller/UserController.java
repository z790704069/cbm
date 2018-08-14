package com.kooola.cloudbookmark.controller;

import com.kooola.cloudbookmark.common.RestResponseModel;
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

    @PostMapping(value = "login")
    @ResponseBody
    public RestResponseModel doLogin(@RequestParam String username, @RequestParam String password,
                                     @RequestParam(required = false) String remeber_me,
                                     HttpServletRequest request, HttpServletResponse response){
        User user = null;
        try{
            user = userService.login(username, password);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            if(StringUtils.isNotBlank(remeber_me)){
                HttpUtil.setCookie(response, user.getUid());
            }
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }
        return new RestResponseModel(ResultConstant.CBM_SUCCESS, user);
    }

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
}
