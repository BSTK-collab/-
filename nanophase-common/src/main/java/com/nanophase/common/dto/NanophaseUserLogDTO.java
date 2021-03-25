package com.nanophase.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhj
 * @since 2021-03-25
 * @apiNote 用户日志信息
 */
@Data
public class NanophaseUserLogDTO implements Serializable {

    /**
     * 主键自增
     */
    private Long nanophaseUserLogId;

    /**
     * user主键
     */
    private Long nanophaseUserId;

    /**
     * 用户邮箱
     */
    private String nanophaseUserEmail;

    /**
     * 用户ip地址
     */
    private String ipAddr;

    /**
     * 是否登录成功 0:成功；1：失败
     */
    private Integer loginStatus;

    /**
     * 登录异常原因
     */
    private String eMessage;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 创建者名称
     */
    private String createUserName;

    /**
     * 记录创建日期 手动赋值
     */
    private LocalDateTime createDate;
}
