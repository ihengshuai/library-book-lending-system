package com.hs.authservice.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MenuModel implements Serializable {


    @Serial
    private static final long serialVersionUID = 1908533027835176706L;

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
}
