package com.hs.auth.service;

import com.hs.auth.model.User;
import com.hs.authservice.entity.AuthUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chenkk
 * @since 2025-12-30
 */
public interface IAuthUserService extends IService<AuthUser> {
    /**
     * 更具用户名查询单个用户信息
     * @param username
     * @return
     */
    User selectOne(String username);
}
