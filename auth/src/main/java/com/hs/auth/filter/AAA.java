package com.hs.auth.filter;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AAA implements AuthenticationProvider {
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println(authentication);
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
