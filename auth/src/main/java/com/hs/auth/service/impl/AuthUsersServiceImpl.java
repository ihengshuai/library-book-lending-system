package com.hs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hs.auth.mapper.AuthUsersMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hs.authservice.entity.AuthUsers;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenkk
 * @since 2025-12-26
 */
@Service
public class AuthUsersServiceImpl extends ServiceImpl<AuthUsersMapper, AuthUsers> implements IService<AuthUsers> {

}
