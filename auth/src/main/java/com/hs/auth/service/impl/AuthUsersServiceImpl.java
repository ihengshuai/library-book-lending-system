package com.hs.auth.service.impl;

import com.hs.auth.entity.AuthUsers;
import com.hs.auth.mapper.AuthUsersMapper;
import com.hs.auth.service.IAuthUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AuthUsersServiceImpl extends ServiceImpl<AuthUsersMapper, AuthUsers> implements IAuthUsersService {

}
