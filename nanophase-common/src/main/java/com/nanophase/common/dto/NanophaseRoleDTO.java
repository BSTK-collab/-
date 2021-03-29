package com.nanophase.common.dto;

import com.nanophase.common.dto.page.PageDTO;
import lombok.Data;

/**
 * @author zhj
 * @since 2021-03-17
 * @apiNote 角色DTO实体类
 */
@Data
public class NanophaseRoleDTO extends PageDTO {

    /**
     * 主键
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 排序字段
     */
    private Integer roleSort;

    /**
     * 是否禁用（0：未禁用，1：已禁用）
     */
    private Integer roleStatus;

    /**
     * 是否删除（0，未删除；1：已删除）
     */
    private Integer roleDelete;

    /**
     * role_user中间表主键
     */
    private Long roleUserId;
}
