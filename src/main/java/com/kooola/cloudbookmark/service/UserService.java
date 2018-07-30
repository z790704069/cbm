package com.kooola.cloudbookmark.service;

import com.kooola.cloudbookmark.domain.User;

/**
 * Created by march on 2018/7/26.
 */
public interface UserService {
    public User login(String username, String password);

    public void register(User user);
}
