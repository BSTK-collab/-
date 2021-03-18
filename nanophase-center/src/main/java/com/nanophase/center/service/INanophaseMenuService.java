package com.nanophase.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanophase.center.entity.NanophaseMenu;
import com.nanophase.common.dto.NanophaseMenuDTO;
import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.util.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
public interface INanophaseMenuService extends IService<NanophaseMenu> {

    /**
     * 批量保存菜单
     *
     * @param menuDTOS
     * @return R boolean
     */
    R insertBatchMenu(List<NanophaseMenuDTO> menuDTOS);

    /**
     * 菜单树形查询
     *
     * @return list嵌套结构
     */
    R getTreeMenu();

    /**
     * 批量修改
     *
     * @param menuDTOS
     * @return boolean
     */
    R updateBatchMenu(List<NanophaseMenuDTO> menuDTOS);

    /**
     * 批量删除
     *
     * @param menuDTOS
     * @return boolean
     */
    R deleteBatchMenu(List<NanophaseMenuDTO> menuDTOS);

    /**
     * 启用或禁用菜单
     *
     * @param nanophaseMenuDTO
     * @return
     */
    R stopOrStartMenu(NanophaseMenuDTO nanophaseMenuDTO);
}
