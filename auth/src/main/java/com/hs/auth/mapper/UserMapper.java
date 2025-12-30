package com.hs.auth.mapper;

import cn.hutool.core.util.StrUtil;

import com.hs.auth.model.Auth;
import com.hs.auth.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {


    public static final String USER_NAME = "admin";
    public User selectOne(String userName){
        if(StrUtil.equals(userName, USER_NAME)){
            User user = new User();
            user.setUserName(userName);
            user.setAuths(new ArrayList<Auth>());
            user.setPassword("$2a$10$Sp92mUJs5saeRY63uVjD3uxFTvUkB8k7PFa1LO9XHQsMjDu4IHIWy");
            return user;

        }else{
            return null;
        }


    }

}
