package com.hs.auth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 加密工具类
 */
public class EncryptUtil {


    public static String bCryptEncoder(String plaintext){
        PasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 10);
        return  encoder.encode(plaintext);
    }
}
