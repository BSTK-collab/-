package com.nanophase.center.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色与菜单中间表
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class NanophaseRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long nanophaseRoleId;

    /**
     * 菜单ID
     */
    private Long nanophaseMenuId;

    /**
     * 逻辑删除（0：未删除；1：已删除）
     */
    private Boolean nanophaseDeleted;

    private String createUser;

    private String createUserName;

    private LocalDateTime createDate;


}
