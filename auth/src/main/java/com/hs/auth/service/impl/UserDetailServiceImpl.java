package com.hs.auth.service.impl;

import com.hs.auth.mapper.AuthUserMapper;
import com.hs.auth.mapper.UserMapper;
import com.hs.auth.model.User;
import com.hs.auth.service.IAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

   public final IAuthUserService authUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authUserService.selectOne(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;	// UserDetailsImpl 是我们实现的类
    }
}
