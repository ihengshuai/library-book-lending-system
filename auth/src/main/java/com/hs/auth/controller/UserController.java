package com.hs.auth.controller;

import com.hs.core.common.ResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenkk
 * @since 2025-12-26
 */
@RestController
@RequestMapping("/blog")
public class UserController {
    @RequestMapping("/login")
    public ResultVo login(){
        return ResultVo.success();
    }

}
