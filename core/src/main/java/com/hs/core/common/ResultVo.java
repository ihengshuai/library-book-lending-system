package com.hs.core.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 公共响应值类
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class ResultVo<T> implements Serializable {


    @Serial
    private static final long serialVersionUID = 178566063659455369L;

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
