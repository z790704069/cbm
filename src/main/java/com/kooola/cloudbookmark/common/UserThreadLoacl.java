package com.kooola.cloudbookmark.common;

import com.kooola.cloudbookmark.domain.User;

/**
 * Created by march on 2018/8/1.
 */
public class UserThreadLoacl {
    private static ThreadLocal<User> threadLocal=new ThreadLocal<User>();

    public static void setValue(User user){
        threadLocal.set(user);
    }

    public static User getUser(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
