package com.hs.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.hs.auth.model.TokenInfo;
import com.hs.auth.utils.SpringContextUtil;
import com.hs.auth.utils.TokenUtil;
import com.hs.authservice.model.MenuModel;
import com.hs.authservice.model.RoleModel;
import com.hs.authservice.model.UserModel;
import com.hs.core.exception.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

        TokenUtil tokenUtil = SpringContextUtil.getBean(TokenUtil.class);
        // 判断token是否有效
        if(!tokenUtil.validateToken(token))    {
            throw new AuthException("500","请登录");
        }


        //认证授权
        doAuthorization(token);
        chain.doFilter(request, response);


    }


    /**
     * 认证授权
     * 1、第一次登陆的接口创建
     *
     * @param token
     */
    private void doAuthorization(String token) {
        TokenUtil tokenUtil = SpringContextUtil.getBean(TokenUtil.class);
        UserModel user = tokenUtil.getUserByToken(token);
        List<RoleModel> roles = user.getRoles();
        Stream<String> roleCodes = roles.stream().map(RoleModel::getCode);
        user.getMenus().stream().map(MenuModel::getPerms);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        //角色编码集合
        List<String> roleCodeList = new ArrayList<>();

        roleCodeList = Optional.ofNullable(roleCodeList).orElse(new ArrayList<>());
        //设置角色
        roleCodes.forEach(r -> {
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
        UsernamePasswordAuthenticationToken userAuthorization = new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorityList);
        // 执行认证
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
