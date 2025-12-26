package com.hs.core.common;

import lombok.Getter;

/**
 * http 接口响应状态码枚举
 */
@Getter
public enum HttpResponseStatus {
    success("200","success"),
    fail("500","fail"),
    ;
    private String code;
    private String desc;


    HttpResponseStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
