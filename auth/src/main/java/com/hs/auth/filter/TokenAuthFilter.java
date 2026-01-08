package com.hs.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.hs.auth.model.TokenInfo;
import com.hs.auth.utils.SpringContextUtil;
import com.hs.auth.utils.TokenUtil;
import com.hs.authservice.model.MenuModel;
import com.hs.authservice.model.RoleModel;
import com.hs.authservice.model.UserModel;
import com.hs.core.exception.AuthException;
import com.hs.core.exception.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
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

        try {
            // 判断token是否有效
            if (!tokenUtil.validateToken(token)) {
                throw new TokenExpiredException("400");
            }



            //认证授权
            doAuthorization(token);
            chain.doFilter(request, response);
        }catch (TokenExpiredException e){
            handleException(response, HttpStatus.UNAUTHORIZED, "TOKEN_EXPIRED", e.getMessage());
        }



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
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if(roles != null){
            Stream<String> roleCodes = roles.stream().map(RoleModel::getCode);
            user.getMenus().stream().map(MenuModel::getPerms);

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
        }




        // 配置token认证用户的权限
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

    /**
     * 统一异常响应处理
     */
    private void handleException(HttpServletResponse response, HttpStatus status, String code, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", code);
        errorResponse.put("msg", message);
        errorResponse.put("timestamp", System.currentTimeMillis());

        // 将异常信息写入响应
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
