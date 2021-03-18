package com.nanophase.center.controller;

import com.nanophase.center.service.INanophaseMenuService;
import com.nanophase.common.annotation.ReadDB;
import com.nanophase.common.annotation.WebLog;
import com.nanophase.common.annotation.WriteDB;
import com.nanophase.common.dto.NanophaseMenuDTO;
import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器 菜单相关业务
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
@RestController
@RequestMapping("/nanophase-menu")
public class NanophaseMenuController {

    @Autowired
    private INanophaseMenuService iNanophaseMenuService;

    /**
     * 批量保存菜单
     *
     * @param menuDTOS
     * @return R boolean
     */
    @WebLog
    @WriteDB
    @PostMapping("/insertBatchMenu")
    public R insertBatchMenu(@RequestBody List<NanophaseMenuDTO> menuDTOS) {
        return iNanophaseMenuService.insertBatchMenu(menuDTOS);
    }

    /**
     * 菜单树形查询
     *
     * @return list嵌套结构
     */
    @ReadDB
    @PostMapping("/getTreeMenu")
    public R getTreeMenu() {
        return iNanophaseMenuService.getTreeMenu();
    }

    /**
     * 批量修改
     *
     * @param menuDTOS
     * @return boolean
     */
    @WebLog
    @WriteDB
    @PostMapping("/updateBatchMenu")
    public R updateBatchMenu(List<NanophaseMenuDTO> menuDTOS) {
        return iNanophaseMenuService.updateBatchMenu(menuDTOS);
    }

    /**
     * 批量删除 该接口不作为修改禁用启用状态的接口
     *
     * @param menuDTOS
     * @return boolean
     */
    @WebLog
    @WriteDB
    @PostMapping("/deleteBatchMenu")
    public R deleteBatchMenu(List<NanophaseMenuDTO> menuDTOS) {
        return iNanophaseMenuService.deleteBatchMenu(menuDTOS);
    }

    /**
     * 启用或禁用菜单 写在update接口会比较麻烦，所以单独了出来
     *
     * @param nanophaseMenuDTO
     * @return
     */
    @WebLog
    @WriteDB
    @PostMapping("/stopOrStartMenu")
    public R stopOrStartMenu(@RequestBody NanophaseMenuDTO nanophaseMenuDTO) {
        return iNanophaseMenuService.stopOrStartMenu(nanophaseMenuDTO);
    }
}
