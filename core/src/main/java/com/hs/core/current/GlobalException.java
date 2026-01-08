package com.hs.core.current;

import com.hs.core.common.ResultVo;
import com.hs.core.exception.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResultVo handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        return ResultVo.fail();
    }

    /**
     * 处理Token过期异常
     */
    @ExceptionHandler(TokenExpiredException.class)
    public ResultVo handleTokenExpiredException(TokenExpiredException e) {
      return new ResultVo("400","TOKEN_EXPIRED");
    }
}
