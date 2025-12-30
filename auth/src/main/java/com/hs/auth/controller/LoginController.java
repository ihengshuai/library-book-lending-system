package com.hs.auth.controller;

import com.hs.authservice.dto.LoginDto;
import com.hs.auth.model.TokenInfo;
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

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {


    public final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginDto loginDto) {


        try {
            /*// 创建认证令牌
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            // 执行认证
            Authentication authentication = authenticationManager.authenticate(authToken);
            // 认证成功，设置安全上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);*/
            // 创建 token
            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.setToken("uuid1234567");
            ResultVo<TokenInfo> result = new ResultVo<>();
            result.setData(tokenInfo);

            return result;

        } catch (AuthenticationException e) {
            // 认证失败，返回登录页面并显示错误信息

            return ResultVo.fail();
        }


    }
}
