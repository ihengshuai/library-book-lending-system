package com.hs.authservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hs.authservice.dto.UserDto;
import com.hs.authservice.entity.AuthUser;
import com.hs.authservice.model.UserModel;
import com.hs.core.common.ResultVo;
import org.springframework.security.core.userdetails.User;

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
    UserModel selectOne(String username);

    /**
     * 新增用户
     * @param userDto
     * @return
     */
    ResultVo addUser(UserDto userDto);
}
