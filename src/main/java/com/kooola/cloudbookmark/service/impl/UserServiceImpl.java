package com.kooola.cloudbookmark.service.impl;

import com.kooola.cloudbookmark.common.exception.MyException;
import com.kooola.cloudbookmark.common.constants.NormalConstant;
import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.dao.UserMapper;
import com.kooola.cloudbookmark.domain.User;
import com.kooola.cloudbookmark.service.UserService;
import com.kooola.cloudbookmark.utils.SecurityUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by march on 2018/7/26.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new MyException(ResultConstant.CBM_USERNAME_PASSWD_EMPTY);  //用户名或密码为空
        }
        User user = userMapper.selectByUsername(username);
        if(null == user){
            throw new MyException(ResultConstant.CBM_USERNAME_NOT_EXIST);  //用户不存在
        }
        if(null != user.getSalt()){
            password = password + user.getSalt();
        }
        if(!SecurityUtil.MD5encode(password).equals(user.getPassword())){
            throw new MyException(ResultConstant.CBM_PASSWORD_NOT_RIGHT);  //密码不正确
        }
        return user;
    }

    @Override
    public void register(User user) {
        if(null != userMapper.selectByUsername(user.getUsername())){
            throw new MyException(ResultConstant.CBM_USER_ALREADY_EXIST);  //用户已经存在
        }
        String salt = RandomStringUtils.randomAlphanumeric(NormalConstant.SALT_LENGTH);  //随机生成固定长度的盐
        user.setSalt(salt);
        user.setPassword(SecurityUtil.MD5encode((user.getPassword() + salt)));

        Long currentTimeStamp = System.currentTimeMillis() / 1000;
        user.setCreateTime(currentTimeStamp);
        user.setUpdateTime(currentTimeStamp);
        userMapper.insert(user);
        return ;
    }
}
