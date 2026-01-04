package com.hs.auth.model;

import com.hs.authservice.model.UserModel;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class TokenInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 5652768429068077049L;
    private UserModel userModel;
    private String token;

}
