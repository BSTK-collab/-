package com.nanophase.center.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author zhj
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("nanophase_system_log")
public class NanophaseSystemLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long systemLogId;

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
    @TableField("creare_user")
    private String creareUser;

    /**
     * 创建者名称
     */
    @TableField("create_user_name")
    private String createUserName;

    /**
     * 创建时间
     */
    @TableField("creare_date")
    private LocalDateTime creareDate;
}
