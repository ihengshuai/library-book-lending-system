package com.hs.auth.model;

import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class User implements UserDetails {


    private String userName;

    private String password;

    private List<Role> roles;

    private List<Auth> auths;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.auths;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
    }
}