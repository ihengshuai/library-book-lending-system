package com.hs.auth.service.impl;

import com.hs.auth.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserCacheService {


    public void setCache(){

    }

    public User getCache(){
        User user = new User();
        user.setUserName("admin");
        user.setAuths(new ArrayList<>());
        return user;
    }
}
