package com.hs.authservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chenkk
 * @since 2025-12-30
 */
@TableName("auth_user")
@Data
@Builder
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态（1：开通、2：关闭、3：锁定）
     */
    private Integer status;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 是否管理员(1：是、0：否)
     */
    private Integer isAdmin;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 锁定时间
     */
    private LocalDateTime lockedTime;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 性别（1：男性、2：女性、3：其他）
     */
    private Integer sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createdDate;

    /**
     * 更新时间
     */
    private LocalDateTime updatedDate;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 更新人
     */
    private String updatedUser;


    @Override
    public String toString() {
        return "AuthUser{" +
            "id = " + id +
            ", username = " + username +
            ", password = " + password +
            ", status = " + status +
            ", name = " + name +
            ", nickname = " + nickname +
            ", isAdmin = " + isAdmin +
            ", email = " + email +
            ", lockedTime = " + lockedTime +
            ", phone = " + phone +
            ", salt = " + salt +
            ", sex = " + sex +
            ", avatar = " + avatar +
            ", createdDate = " + createdDate +
            ", updatedDate = " + updatedDate +
            ", createdUser = " + createdUser +
            ", updatedUser = " + updatedUser +
            "}";
    }
}
