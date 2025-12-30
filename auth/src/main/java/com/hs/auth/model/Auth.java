package com.hs.auth.model;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public class Auth implements GrantedAuthority {
    @Override
    public @Nullable String getAuthority() {
        return "";
    }
}
