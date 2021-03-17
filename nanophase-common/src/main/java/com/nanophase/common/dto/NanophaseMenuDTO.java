package com.nanophase.common.dto;

import lombok.Data;

/**
 * @author zhj
 * @since 2021-03-17
 * @apiNote 菜单实体类DTO
 */
@Data
public class NanophaseMenuDTO {
    /**
     * 主键
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父级ID
     */
    private Long menuParentId;

    /**
     * 排序字段
     */
    private Integer menuSort;

    /**
     * 菜单状态（0：未禁用；1：已禁用）
     */
    private Integer menuStatus;

    /**
     * 显示状态（0：显示；1：未显示）
     */
    private Integer menuVisible;
}
