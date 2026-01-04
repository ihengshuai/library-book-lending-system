package com.hs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hs.auth.mapper.AuthMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hs.authservice.entity.AuthMenu;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author chenkk
 * @since 2026-01-04
 */
@Service
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuMapper, AuthMenu> implements IService<AuthMenu> {

}
