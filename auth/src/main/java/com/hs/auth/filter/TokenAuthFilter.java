package com.hs.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.hs.auth.model.TokenInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 自定义 token 过滤器
 */
public class TokenAuthFilter extends BasicAuthenticationFilter {


    public TokenAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = getRequestToken(request);

        //请求头中没有Token信息则直接放行
        if (StrUtil.isBlank(token)) {
            chain.doFilter(request, response);
            return;
        }

        //认证授权
        doAuthorization(new TokenInfo(), token);
        chain.doFilter(request, response);


    }


    /**
     * 认证授权
     * 1、第一次登陆的接口创建
     *
     * @param tokenInfo
     * @param token
     */
    private void doAuthorization(TokenInfo tokenInfo, String token) {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        //角色编码集合
        List<String> roleCodeList = new ArrayList<>();
        roleCodeList = Optional.ofNullable(roleCodeList).orElse(new ArrayList<>());
        //设置角色
        roleCodeList.forEach(r -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + r));
        });
        //权限标识集合
        List<String> permissionIdList = new ArrayList<>();
        permissionIdList = Optional.ofNullable(permissionIdList).orElse(new ArrayList<>());
        //设置权限
        permissionIdList.forEach(p -> {
            authorityList.add(new SimpleGrantedAuthority(p));
        });
        //配置token认证用户的权限
        UsernamePasswordAuthenticationToken userAuthorization = new UsernamePasswordAuthenticationToken("admin", token, authorityList);

        SecurityContextHolder.getContext().setAuthentication(userAuthorization);

    }

    /**
     * 解析token
     *
     * @param request jwt
     * @return token中的用户信息
     */
    private String getRequestToken(HttpServletRequest request) {
        System.out.println(request.getHeader("token"));
        return request.getHeader("token");
    }

    /**
     * 获取用户头信息
     *
     * @param request jwt
     * @return token中的用户信息
     */
    private String getUserHeader(HttpServletRequest request) {
        return request.getHeader("User");
    }
}
