package com.hs.core.current;

import com.hs.core.common.ResultVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResultVo handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        return ResultVo.fail();
    }
}
