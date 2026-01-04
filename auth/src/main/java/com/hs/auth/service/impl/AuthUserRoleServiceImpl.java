package com.hs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hs.auth.mapper.AuthUserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hs.authservice.entity.AuthUserRole;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author chenkk
 * @since 2026-01-04
 */
@Service
public class AuthUserRoleServiceImpl extends ServiceImpl<AuthUserRoleMapper, AuthUserRole> implements IService<AuthUserRole> {

}
