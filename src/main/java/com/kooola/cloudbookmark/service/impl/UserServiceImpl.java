package com.kooola.cloudbookmark.service.impl;

import com.kooola.cloudbookmark.common.exception.MyException;
import com.kooola.cloudbookmark.common.constants.NormalConstant;
import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.dao.UserMapper;
import com.kooola.cloudbookmark.domain.User;
import com.kooola.cloudbookmark.service.UserService;
import com.kooola.cloudbookmark.utils.MailUtil;
import com.kooola.cloudbookmark.utils.SecurityUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

/**
 * Created by march on 2018/7/26.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String email, String password) {
        if(StringUtils.isBlank(email) || StringUtils.isBlank(password)){
            throw new MyException(ResultConstant.CBM_USERNAME_PASSWD_EMPTY);  //用户名或密码为空
        }
        User user = userMapper.selectByEmail(email);
        if(null == user){
            throw new MyException(ResultConstant.CBM_USERNAME_NOT_EXIST);  //用户不存在
        }
        if(0 == user.getActivation()){
            throw new MyException(ResultConstant.CBM_USER_NOT_ACTIVE);  //用户未激活，无法登陆，需要邮箱激活
        }
        if(null != user.getSalt()){
            password = password + user.getSalt();
        }
        if(!SecurityUtil.MD5encode(password).equals(user.getPassword())){
            throw new MyException(ResultConstant.CBM_PASSWORD_NOT_RIGHT);  //密码不正确
        }
        //隐藏密码和盐
        user.setPassword(null);
        user.setSalt(null);
        return user;
    }

    @Override
    public void register(User user) {
        /**
         * 1、判断邮箱和密码是否为空
         * 2、判断用户是否已存在
         * 3、密码加盐加密
         * 4、保存数据库 默认未激活 设置随机激活码
         * 5、发送邮件到用户邮箱
         */
        if(StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword())){
            throw new MyException(ResultConstant.CBM_USERNAME_PASSWD_EMPTY); //用户名或密码不能为空
        }
        if(null != userMapper.selectByEmail(user.getEmail())){
            throw new MyException(ResultConstant.CBM_USER_ALREADY_EXIST);  //用户已经存在
        }
        String salt = RandomStringUtils.randomAlphanumeric(NormalConstant.SALT_LENGTH);  //随机生成固定长度的盐
        user.setSalt(salt);
        user.setPassword(SecurityUtil.MD5encode((user.getPassword() + salt)));

        Long currentTimeStamp = System.currentTimeMillis() / 1000;
        user.setCreateTime(currentTimeStamp);
        user.setUpdateTime(currentTimeStamp);
        user.setActivation(0); //默认未激活
        UUID uuid = UUID.randomUUID();
        user.setActivationCode(uuid.toString());
        userMapper.insert(user);

        //组装邮件验证（激活链接）
        String url = NormalConstant.CBM_MAKE_ACTIVATION_URL + "?email=" + user.getEmail() + "&code=" + uuid.toString();
        LOG.info("Email message for activation of user: " + url);
        try{
            MailUtil.sendMailMessage(user.getEmail(), url);
        }catch (Exception e){
            throw new MyException(ResultConstant.CBM_SEND_MAIL_ACTIVE_FAIL);
        }
    }

    /**
     * 用户邮箱已经收到激活url,此时进行激活，数据库中激活状态变成已激活
     * @param email
     * @param code
     */
    @Override
    public void active(String email, String code) {
        User user = userMapper.selectByEmail(email);
        if(null == user){
            throw new MyException(ResultConstant.CBM_USERNAME_NOT_EXIST);
        }
        if(user.getActivation() == 1){
            throw new MyException(ResultConstant.CBM_USER_ALEARDY_ACTIVE);
        }
        Long currentTimeStamp = System.currentTimeMillis() / 1000;
        if(currentTimeStamp - user.getUpdateTime() > NormalConstant.CBM_ACTIVE_DURING_TIME){
            throw new MyException(ResultConstant.CBM_ACTIVE_TIME_OVER);  //激活码过期，应在CBM_ACTIVE_DURING_TIME时间内进行激活
        }
        user.setActivation(1);
        userMapper.updateActivationByUId(user);
    }

    /**
     * 用户可能未激活无法登陆，此时请求激活
     * 1、更新激活码
     * 2、将新的激活码（邮箱地址+激活码组成的url）通过邮件的方式发送给用户邮箱
     * @param email
     */
    @Override
    public void makeActivation(String email) {
        User user = userMapper.selectByEmail(email);
        if(null == user){
            throw new MyException(ResultConstant.CBM_USERNAME_NOT_EXIST);
        }
        if(user.getActivation() == 1){
            throw new MyException(ResultConstant.CBM_USER_ALEARDY_ACTIVE);
        }
        UUID uuid = UUID.randomUUID();
        user.setActivationCode(uuid.toString());
        Long currentTimeStamp = System.currentTimeMillis() / 1000;
        user.setUpdateTime(currentTimeStamp);
        userMapper.updateActivationByUId(user);

        //组装邮件验证（激活链接）
        String url = NormalConstant.CBM_MAKE_ACTIVATION_URL + "?email=" +
                user.getEmail() + "&code=" + uuid.toString();
        LOG.info("Email message for activation of user: " + url);
        try{
            MailUtil.sendMailMessage(user.getEmail(), url);
        }catch (Exception e) {
            throw new MyException(ResultConstant.CBM_SEND_MAIL_ACTIVE_FAIL);
        }
    }

    /**
     * 修改用户密码
     * @param user 当前用户
     * @param oldPasswd  旧密码
     * @param newPasswd  新密码
     * 1、判断旧密码是否是当前用户密码
     * 2、修改密码
     */
    @Override
    public void changePasswd(User user, String oldPasswd, String newPasswd) {
        String salt = user.getSalt();
        String passwd = SecurityUtil.MD5encode((oldPasswd + salt));
        if(!passwd.equals(user.getPassword())){
            throw new MyException(ResultConstant.CBM_PASSWORD_NOT_RIGHT);
        }
        user.setPassword(SecurityUtil.MD5encode(newPasswd + salt));
    }
}
