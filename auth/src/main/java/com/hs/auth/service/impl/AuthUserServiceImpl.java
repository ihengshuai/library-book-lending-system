package com.hs.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hs.auth.model.User;
import com.hs.authservice.entity.AuthUser;
import com.hs.auth.mapper.AuthUserMapper;
import com.hs.auth.service.IAuthUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenkk
 * @since 2025-12-30
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {

    @Override
    public User selectOne(String username) {
        User user = null;
        QueryWrapper<AuthUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        AuthUser authUser = this.baseMapper.selectOne(queryWrapper);
        if(null != authUser){
           user = new User();
           user.setUserName(authUser.getUsername());
           user.setPassword(authUser.getPassword());
           user.setAuths(new ArrayList<>());
           user.setRoles(new ArrayList<>());
        }
        return user;

    }
}
