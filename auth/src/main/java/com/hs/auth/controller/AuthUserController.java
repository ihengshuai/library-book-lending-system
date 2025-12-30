package com.hs.auth.controller;

import com.hs.auth.service.IAuthUserService;
import com.hs.authservice.dto.UserDto;
import com.hs.authservice.entity.AuthUser;
import com.hs.core.common.ResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author chenkk
 * @since 2025-12-30
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthUserController {

    public final IAuthUserService authUserService;
    @PostMapping("/addUer")
    public ResultVo<AuthUser> addUser(@RequestBody UserDto userDto){
        return ResultVo.success();

    }
}
