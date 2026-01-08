package com.hs.auth.config;


import com.hs.auth.filter.TokenAuthFilter;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ServletRequest httpServletRequest) throws Exception {

        http
                // 开启授权保护
                .authorizeHttpRequests(authorize -> authorize
                        // 不需要认证的地址有哪些
                        .requestMatchers("/login", "/register", "/blog/**", "/public/**", "/about").permitAll()    // ** 通配符
                        // 对所有请求开启授权保护
                        .anyRequest()
                        // 已认证的请求会被自动授权
                        .authenticated());

        //鉴权 自定义过滤器
        http.addFilterAt(new TokenAuthFilter(this.authenticationManager), BasicAuthenticationFilter.class);
        // 关闭 csrf CSRF（跨站请求伪造）是一种网络攻击，攻击者通过欺骗已登录用户，诱使他们在不知情的情况下向受信任的网站发送请求。
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }




    @Bean("authenticationManager")
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 10); // 使用 bcrypt 加密
    }


}
