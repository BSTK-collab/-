package com.nanophase.center.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
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
    private Long nanophaseUserId;

    /**
     * 用户邮箱
     */
    private String nanophaseUserEamil;

    private String createUser;

    private String createUserName;

    private LocalDateTime createDate;


}
