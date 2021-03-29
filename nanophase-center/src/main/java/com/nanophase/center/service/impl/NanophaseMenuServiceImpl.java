package com.nanophase.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanophase.center.entity.NanophaseMenu;
import com.nanophase.center.mapper.NanophaseMenuMapper;
import com.nanophase.center.service.INanophaseMenuService;
import com.nanophase.center.warper.MenuWarper;
import com.nanophase.common.constant.CenterConstant;
import com.nanophase.common.dto.NanophaseMenuDTO;
import com.nanophase.common.handler.NanophaseException;
import com.nanophase.common.util.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单服务实现类
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
@Service
public class NanophaseMenuServiceImpl extends ServiceImpl<NanophaseMenuMapper, NanophaseMenu> implements INanophaseMenuService {

    /**
     * 批量保存菜单
     *
     * @param menuDTOS
     * @return R boolean
     */
    @Override
    public R insertBatchMenu(List<NanophaseMenuDTO> menuDTOS) {
        // 校验新增的参数是否合法
        verifyMenuParam(menuDTOS, false);
        List<NanophaseMenu> nanophaseMenus = new ArrayList<>();
        for (NanophaseMenuDTO menuDTO : menuDTOS) {
            NanophaseMenu nanophaseMenu = MenuWarper.INSTANCE.targetToSource(menuDTO);
            if (null == nanophaseMenu.getMenuParentId()) {
                nanophaseMenu.setMenuParentId(0);
            }
            if (null == nanophaseMenu.getMenuSort()) {
                nanophaseMenu.setMenuSort(1);
            }
            nanophaseMenus.add(nanophaseMenu);
        }
        return R.success().put("data", this.saveBatch(nanophaseMenus));
    }

    /**
     * 校验参数是否合法
     *
     * @param menuDTOS
     */
    private void verifyMenuParam(List<NanophaseMenuDTO> menuDTOS, boolean isUpdate) {
        List<String> menuNames = new ArrayList<>();
        if (null != menuDTOS && menuDTOS.size() > 0) {
            // 菜单名称不能为空
            for (NanophaseMenuDTO menuDTO : menuDTOS) {
                if (StringUtils.isBlank(menuDTO.getMenuName())) {
                    throw new NanophaseException("Menu name cannot be empty");
                }
                if (isUpdate) {
                    Integer menuId = menuDTO.getMenuId();
                    if (null == menuDTO.getMenuId()) {
                        // 修改操作时 主键不能为空
                        throw new NanophaseException("Menu id cannot be empty");
                    }
                    NanophaseMenu nanophaseMenu = this.getById(menuId);
                    if (null == nanophaseMenu) {
                        throw new NanophaseException("菜单不存在");
                    }
                    // TODO: 2021/3/29 修改菜单时 应限制数量
                    NanophaseMenu menu = this.getOne(new QueryWrapper<NanophaseMenu>().eq("menu_deleted", 0)
                            .eq("menu_name", menuDTO.getMenuName()));
                    if (null != menu && !menu.getMenuId().equals(menuId)) {
                        throw new NanophaseException("Menu name already exist");
                    }
                }
                menuNames.add(menuDTO.getMenuName());
            }
            List<NanophaseMenu> list = this.list(new QueryWrapper<NanophaseMenu>().eq("menu_deleted", 0).in("menu_name", menuNames));
            if (null != list && list.size() > 0 && !isUpdate) {
                throw new NanophaseException("Menu name already exist");
            }
        }
    }

    /**
     * 菜单树形查询
     *
     * @return list嵌套结构
     */
    @Override
    public R getTreeMenu() {
        // 获取所有菜单数据
        List<NanophaseMenu> nanophaseMenus = this.list(new QueryWrapper<NanophaseMenu>().eq("menu_deleted", 0).eq("menu_visible", 0));
        if (nanophaseMenus != null && nanophaseMenus.size() > 0) {
            return R.success().put("data", nanophaseMenus.stream()
                    .filter(menu -> menu.getMenuParentId().equals(0))
                    .map(menu -> getChildMenu(menu, nanophaseMenus))
                    .sorted(Comparator.comparing(NanophaseMenuDTO::getMenuSort))
                    .collect(Collectors.toList()));
        }
        return R.success();
    }

    /**
     * 批量修改 该接口不作为修改禁用启用状态的接口
     *
     * @param menuDTOS
     * @return boolean
     */
    @Override
    public R updateBatchMenu(List<NanophaseMenuDTO> menuDTOS) {
        verifyMenuParam(menuDTOS, true);
        return R.success().put("data", this.updateBatchById(MenuWarper.INSTANCE.targetToSource(menuDTOS)));
    }

    /**
     * 批量删除
     *
     * @param menuDTOS
     * @return boolean
     */
    @Override
    public R deleteBatchMenu(List<NanophaseMenuDTO> menuDTOS) {
        List<NanophaseMenu> nanophaseMenus = MenuWarper.INSTANCE.targetToSource(menuDTOS);
        // 根据ID查询DB
        List<Integer> menuIds = nanophaseMenus.stream().map(NanophaseMenu::getMenuId).collect(Collectors.toList());
        List<NanophaseMenu> list = this.list(new QueryWrapper<NanophaseMenu>().eq("menu_deleted", 0)
                .eq("menu_visible", 0).in("menu_parent_id", menuIds).select("menu_id"));
        // 存在未删除的子菜单 则不允许删除
        if (null != list && list.size() > 0) {
            // 判断子菜单是否存在要删除的列表中
            List<NanophaseMenu> menuIdsFromDb = list.stream().filter(m -> !menuIds.contains(m.getMenuId())).collect(Collectors.toList());
            if (menuIdsFromDb.size() > 0) {
                // 存在子菜单 请先删除子菜单
                throw new NanophaseException("Submenu exists, please delete the submenu first");
            }
            // TODO: 2021/3/18  菜单删除时需要设为禁用
        }
        for (NanophaseMenu nanophaseMenu : nanophaseMenus) {
            if (nanophaseMenu.getMenuId() == null) {
                throw new NanophaseException("Menu id cannot be empty");
            }
            // 逻辑删除
            nanophaseMenu.setMenuDeleted(1);
            nanophaseMenu.setMenuName(null);
            nanophaseMenu.setMenuParentId(null);
            nanophaseMenu.setMenuSort(null);
            nanophaseMenu.setMenuStatus(null);
        }
        return R.success().put("data", this.updateBatchById(nanophaseMenus));
    }

    /**
     * 启用或禁用菜单
     *
     * @param nanophaseMenuDTO
     * @return
     */
    @Override
    public R stopOrStartMenu(NanophaseMenuDTO nanophaseMenuDTO) {
        if (null == nanophaseMenuDTO.getMenuId()) {
            throw new NanophaseException("Menu id cannot be empty");
        }
        Integer menuStatus = nanophaseMenuDTO.getMenuStatus();
        if (null == menuStatus) {
            throw new NanophaseException("Menu status cannot be empty");
        }
        // 禁用
        if (menuStatus.equals(CenterConstant.status.STATUS_ONE)) {
            List<NanophaseMenu> list = this.list(new QueryWrapper<NanophaseMenu>().eq("menu_deleted", 0)
                    .eq("menu_visible", 0).eq("menu_status", 0)
                    .eq("menu_parent_id", nanophaseMenuDTO.getMenuId()));
            if (null != list && list.size() > 0) {
                // 存在未禁用的子菜单
                throw new NanophaseException("Submenu exists, please stop the submenu first");
            }
        } else if (menuStatus.equals(CenterConstant.status.STATUS_ZERO) && null != nanophaseMenuDTO.getMenuParentId()) {
            // 启用 获取是否存在禁用状态的上级菜单
            List<NanophaseMenu> list = this.list(new QueryWrapper<NanophaseMenu>().eq("menu_deleted", 0)
                    .eq("menu_visible", 0).eq("menu_status", 1)
                    .eq("menu_id", nanophaseMenuDTO.getMenuParentId()));
            if (null != list && list.size() > 0) {
                throw new NanophaseException("请先启用上级菜单");
            }
        }

        NanophaseMenu nanophaseMenu = MenuWarper.INSTANCE.targetToSource(nanophaseMenuDTO);
        nanophaseMenu.setMenuName(null);
        nanophaseMenu.setMenuVisible(null);
        nanophaseMenu.setMenuDeleted(null);
        return R.success().put("data", this.updateById(nanophaseMenu));
    }

    /**
     * 递归构建菜单树
     *
     * @param menu
     * @param nanophaseMenus
     * @return
     */
    private NanophaseMenuDTO getChildMenu(NanophaseMenu menu, List<NanophaseMenu> nanophaseMenus) {
        NanophaseMenuDTO nanophaseMenuDTO = MenuWarper.INSTANCE.sourceToTarget(menu);
        List<NanophaseMenuDTO> collect = nanophaseMenus.stream()
                .filter(m -> m.getMenuParentId().equals(menu.getMenuId()))
                .map(m -> getChildMenu(m, nanophaseMenus))
                .sorted(Comparator.comparing(NanophaseMenuDTO::getMenuSort))
                .collect(Collectors.toList());
        nanophaseMenuDTO.setChildMenus(collect);
        return nanophaseMenuDTO;
    }
}
