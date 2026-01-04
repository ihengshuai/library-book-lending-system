package com.hs.auth.controller;

import com.hs.authservice.dto.UserDto;
import com.hs.authservice.entity.AuthUser;
import com.hs.authservice.service.IAuthUserService;
import com.hs.core.common.ResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author chenkk
 * @since 2025-12-30
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthUserController {

    public final IAuthUserService authUserService;

    @PostMapping("/addUer")
    public ResultVo<AuthUser> addUser(@RequestBody UserDto userDto) {
        return ResultVo.success();

    }

    @PreAuthorize("hasRole('ad')")
    @GetMapping("/getUser")
    public ResultVo getUser() {
        return ResultVo.success();
    }
}
