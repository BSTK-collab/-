package com.nanophase.common.handler;

/**
 * @author zhj
 * @date 2021-03-08
 * 自定义异常 TODO 待完善
 */
public class NanophaseException extends RuntimeException {

    /**
     * 错误码
     */
    private int errCode;

    public NanophaseException(String message) {
        super(message);
    }
}
