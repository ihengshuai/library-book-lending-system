package com.hs.authservice.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
@Data
@ToString
public class LoginDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -2338717660062595574L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
