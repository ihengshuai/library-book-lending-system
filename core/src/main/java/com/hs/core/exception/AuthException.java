package com.hs.core.exception;

import lombok.Data;

import java.io.Serial;

@Data
public class AuthException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = -8208340299227034718L;

    private String errCode;

    private String errMsg;

    public AuthException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
