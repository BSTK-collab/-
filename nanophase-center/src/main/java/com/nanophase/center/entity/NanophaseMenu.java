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
 * 
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("nanophase_menu")
public class NanophaseMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 父级ID
     */
    @TableField("menu_parent_id")
    private Integer menuParentId;

    /**
     * 排序字段
     */
    @TableField("menu_sort")
    private Integer menuSort;

    /**
     * 菜单状态（0：未禁用；1：已禁用）
     */
    @TableField("menu_status")
    private Integer menuStatus;

    /**
     * 显示状态（0：显示；1：未显示）
     */
    @TableField("menu_visible")
    private Integer menuVisible;

    /**
     * 逻辑删除字段（0：未删除；1：已删除）
     */
    @TableField("menu_deleted")
    private Integer menuDeleted;
}
