package com.hs.authservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author chenkk
 * @since 2026-01-04
 */
@Data
@TableName("auth_user_role")
public class AuthUserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long roleId;


    @Override
    public String toString() {
        return "AuthUserRole{" +
            "id = " + id +
            ", userId = " + userId +
            ", roleId = " + roleId +
            "}";
    }
}
