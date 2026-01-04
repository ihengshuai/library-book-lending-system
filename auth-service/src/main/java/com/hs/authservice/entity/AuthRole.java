package com.hs.authservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author chenkk
 * @since 2026-01-04
 */
@Data
@TableName("auth_role")
public class AuthRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

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
        return "AuthRole{" +
            "id = " + id +
            ", name = " + name +
            ", description = " + description +
            ", createdDate = " + createdDate +
            ", updatedDate = " + updatedDate +
            ", createdUser = " + createdUser +
            ", updatedUser = " + updatedUser +
            "}";
    }
}
