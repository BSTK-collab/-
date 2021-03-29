package com.nanophase.center.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户与角色中间表
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("nanophase_user_role")
public class NanophaseUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("nanophase_user_id")
    private Long nanophaseUserId;

    /**
     * 角色ID
     */
    @TableField("nanophase_role_id")
    private Long nanophaseRoleId;

    /**
     * 是否删除（0：未删除；1：已删除）
     */
    @TableField("nanophase_deleted")
    private Integer nanophaseDeleted;
}
