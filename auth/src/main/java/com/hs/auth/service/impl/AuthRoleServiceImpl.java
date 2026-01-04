package com.hs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hs.auth.mapper.AuthRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hs.authservice.entity.AuthRole;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author chenkk
 * @since 2026-01-04
 */
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements IService<AuthRole> {

}
