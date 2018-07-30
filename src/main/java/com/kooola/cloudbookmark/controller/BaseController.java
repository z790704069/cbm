package com.kooola.cloudbookmark.controller;

import com.kooola.cloudbookmark.common.RestResponseBo;
import com.kooola.cloudbookmark.common.ResultConstants;
import com.kooola.cloudbookmark.domain.User;
import com.kooola.cloudbookmark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by march on 2018/7/26.
 */

@RestController
public class BaseController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "login")
    @ResponseBody
    public RestResponseBo doLogin(@RequestParam String username, @RequestParam String password){
        User user = null;
        try{
            user = userService.login(username, password);
        }catch (Exception e){
            return new RestResponseBo(e.getMessage());
        }
        return new RestResponseBo(ResultConstants.SUCCESS, user);
    }

    @GetMapping(value = "register")
    @ResponseBody
    public RestResponseBo doRegister(User user){
        try{
            userService.register(user);
        }catch (Exception e){
            return new RestResponseBo(e.getMessage());
        }

        return new RestResponseBo(ResultConstants.SUCCESS);
    }
}
