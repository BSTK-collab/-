package com.nanophase.common.enums;

/**
 * @author zhj
 * @apiNote 错误状态码枚举类
 * @since 2021-03-12
 */
public enum ErrorCodeEnum {
    OK(200, "执行成功"),

    USER_DISABLED(5002, "账号被禁用"),

    FAIL(500, "执行失败"),

    USERNAME_PASSWORD_ERROR(5001, "用户名或密码错误");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 返回信息
     */
    private final String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
