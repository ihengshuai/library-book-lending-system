package com.hs.auth.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.hs.auth.utils.TokenUtil;
import com.hs.authservice.dto.LoginDto;
import com.hs.auth.model.TokenInfo;
import com.hs.authservice.model.UserModel;
import com.hs.authservice.service.IAuthUserService;
import com.hs.core.common.ResultVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {


    public final AuthenticationManager authenticationManager;

    public final IAuthUserService authUserService;

    public final TokenUtil tokenUtil;

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginDto loginDto) {


        try {
            // 创建认证令牌
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            // 执行认证
            Authentication authentication = authenticationManager.authenticate(authToken);

            // 认证成功，设置安全上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 创建 token

            UserModel userModel = authUserService.selectOne(loginDto.getUsername());

            userModel.setPassword("****");

            String token = tokenUtil.generateToken(userModel);

            TokenInfo tokenInfo = new TokenInfo();

            tokenInfo.setToken(token);
            tokenInfo.setUserModel(userModel);
            ResultVo success = ResultVo.success();
            success.setData(tokenInfo);

            return success;
        } catch (AuthenticationException e) {
            // 认证失败，返回登录页面并显示错误信息

            return ResultVo.fail();
        }

    }
}
