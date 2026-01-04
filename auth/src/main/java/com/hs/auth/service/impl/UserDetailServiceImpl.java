package com.hs.auth.service.impl;

import com.hs.authservice.model.UserModel;
import com.hs.authservice.service.IAuthUserService;
import com.hs.core.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

   public final IAuthUserService authUserService;


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        UserModel userModel = authUserService.selectOne(username);

        if(null == userModel ){
            throw new AuthException("403", "用户不存在2");
        }

        user = new User(userModel.getUsername(), userModel.getPassword(),new ArrayList<>());
        //todo  查权限 角色
        return user;	// UserDetailsImpl 是我们实现的类
    }
}
