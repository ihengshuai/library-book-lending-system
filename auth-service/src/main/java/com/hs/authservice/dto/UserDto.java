package com.hs.authservice.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4818362310174969100L;

    private String id;

    private String username;

    private String password;

    private String nickName;

    private String sex;

    private String email;

    private String phone;

    private String userStatus;

    private LocalDateTime createdTime;

    private String createdUser;

    private LocalDateTime updateTime;

    private String updateUser;
}
