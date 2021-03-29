package com.nanophase.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhj
 * @since 2021-03-29
 * @apiNote 系统日志实体类
 */
@Data
public class NanophaseSystemLogDTO implements Serializable {
    /**
     * 主键
     */
    private Integer systemLogId;

    /**
     * 任务名称
     */
    private String systemLogName;

    /**
     * 操作模块
     */
    private String systemLogGroup;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求方式
     */
    private String requestType;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 创建者
     */
    private String creareUser;

    /**
     * 创建者名称
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private LocalDateTime creareDate;
}
