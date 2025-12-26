package com.hs.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hs.auth.dto.LoginDto;
import com.hs.core.common.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/auth")
public class LoginController {

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginDto loginDto){
        log.info("login:{}", JSON.toJSONString(loginDto));
        System.out.println(loginDto);
        if(StrUtil.equals(loginDto.getUsername(),"admin")){
            return ResultVo.success();
        }else{
            return ResultVo.fail();
        }

    }
}
