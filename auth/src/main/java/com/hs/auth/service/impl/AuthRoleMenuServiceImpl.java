package com.hs.auth.service.impl;

import com.hs.authservice.entity.AuthRoleMenu;
import com.hs.auth.mapper.AuthRoleMenuMapper;
import com.hs.authservice.service.IAuthRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author chenkk
 * @since 2026-01-04
 */
@Service
public class AuthRoleMenuServiceImpl extends ServiceImpl<AuthRoleMenuMapper, AuthRoleMenu> implements IAuthRoleMenuService {

}
