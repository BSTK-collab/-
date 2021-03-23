package com.nanophase.center.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *  用户登录日志表
 * </p>
 *
 * @author zhj
 * @since 2021-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("nanophase_user_log")
public class NanophaseUserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    @TableId(value = "nanophase_user_log_id", type = IdType.AUTO)
    private Long nanophaseUserLogId;

    /**
     * user主键
     */
    @TableField("nanophase_user_id")
    private Long nanophaseUserId;

    /**
     * 用户邮箱
     */
    @TableField("nanophase_user_email")
    private String nanophaseUserEmail;

    /**
     * 用户ip地址
     */
    @TableField("ip_addr")
    private String ipAddr;

    /**
     * 是否登录成功 0:成功；1：失败
     */
    @TableField("login_status")
    private Integer loginStatus;

    /**
     * 登录异常原因
     */
    @TableField("e_message")
    private String eMessage;

    /**
     * 创建者
     */
    @TableField(value = "create_user",fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建者名称
     */
    @TableField(value = "create_user_name",fill = FieldFill.INSERT)
    private String createUserName;

    /**
     * 记录创建日期 手动赋值
     */
    @TableField(value = "create_date",fill = FieldFill.INSERT)
    private LocalDateTime createDate;
}
