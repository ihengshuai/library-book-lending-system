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
 * 菜单表
 * </p>
 *
 * @author chenkk
 * @since 2026-01-04
 */
@Data
@TableName("auth_menu")
public class AuthMenu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String ename;

    /**
     * 资源权限标识
     */
    private String perms;

    /**
     * 前端跳转url
     */
    private String path;

    /**
     * 后端请求路径
     */
    private String url;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 资源ICON
     */
    private String icon;

    /**
     * 排序顺序
     */
    private Integer sort;

    /**
     * 资源类型：0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 删除状态：0：已删除     1 正常
     */
    private Integer delFlag;

    /**
     * 是否隐藏：0：不隐藏 ， 1：隐藏
     */
    private Integer hide;

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

    /**
     * 级别
     */
    private String level;


    @Override
    public String toString() {
        return "AuthMenu{" +
                "id = " + id +
                ", name = " + name +
                ", ename = " + ename +
                ", perms = " + perms +
                ", path = " + path +
                ", url = " + url +
                ", parentId = " + parentId +
                ", icon = " + icon +
                ", sort = " + sort +
                ", type = " + type +
                ", delFlag = " + delFlag +
                ", hide = " + hide +
                ", createdDate = " + createdDate +
                ", updatedDate = " + updatedDate +
                ", createdUser = " + createdUser +
                ", updatedUser = " + updatedUser +
                ", level = " + level +
                "}";
    }
}
