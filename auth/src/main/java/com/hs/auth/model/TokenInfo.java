package com.hs.auth.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class TokenInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 5652768429068077049L;

    private User user;

    private Auth auth;

    private Role role;

    private String token;

}
