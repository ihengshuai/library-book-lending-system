package com.hs.core.common;

import lombok.Data;

/**
 * 公共响应值类
 *
 * @param <T>
 */
@Data
public class ResultVo<T> {


    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;


    public ResultVo() {

    }

    public ResultVo(HttpResponseStatus status) {
        this.code = status.getCode();
        this.msg = status.getDesc();
    }

    public static ResultVo success() {
        return new ResultVo(HttpResponseStatus.success);
    }

    public static ResultVo fail() {
        return new ResultVo(HttpResponseStatus.fail);
    }


}
