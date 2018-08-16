package com.kooola.cloudbookmark.dao;

import com.kooola.cloudbookmark.domain.User;

/**
 * Created by march on 2018/7/26.
 */
public interface UserMapper {

    public User selectByPrimaryKey(Integer uid);

    public User selectByEmail(String email);

    public int deleteByPrimaryKey(Integer uid);

    public int insert(User user);

    public int updateActivationByUId(User user);
}
