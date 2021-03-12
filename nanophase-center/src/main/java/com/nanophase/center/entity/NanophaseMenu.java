package com.nanophase.center.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class NanophaseMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
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
