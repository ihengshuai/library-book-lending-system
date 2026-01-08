package com.hs.auth.constant;

import lombok.Getter;

/**
 * 用户状态枚举
 */
@Getter
public enum UserStatus {
    /**
     * 开通状态
     */
    ENABLE(1, "开通"),

    /**
     * 关闭状态
     */
    DISABLE(2, "关闭"),

    /**
     * 锁定状态
     */
    LOCKED(3, "锁定");

    /**
     * 状态编码（存储到数据库的数值）
     */
    private final Integer code;

    /**
     * 状态描述（用于前端展示/日志输出）
     */
    private final String desc;

    // 构造方法
    UserStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据编码获取枚举实例（常用工具方法）
     *
     * @param code 状态编码（1/2/3）
     * @return 对应的枚举实例，无匹配时返回null
     */
    public static UserStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (UserStatus status : UserStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 校验编码是否为有效状态
     *
     * @param code 状态编码
     * @return true=有效，false=无效
     */
    public static boolean isValidCode(Integer code) {
        return getByCode(code) != null;
    }
}
