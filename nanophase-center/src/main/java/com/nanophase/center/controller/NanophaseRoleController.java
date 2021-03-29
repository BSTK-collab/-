package com.nanophase.center.controller;

import com.nanophase.center.service.INanophaseRoleService;
import com.nanophase.common.annotation.ReadDB;
import com.nanophase.common.annotation.WebLog;
import com.nanophase.common.annotation.WriteDB;
import com.nanophase.common.dto.NanophaseRoleDTO;
import com.nanophase.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
@RestController
@RequestMapping("/role/nanophase-role")
public class NanophaseRoleController {

    @Autowired
    private INanophaseRoleService iNanophaseRoleService;

    /**
     * 分页查询角色信息
     *
     * @param nanophaseRoleDTO
     * @return R
     */
    @ReadDB
    @WebLog(value = "分页查询role业务")
    @PostMapping("/page")
    public R getRolePage(@RequestBody NanophaseRoleDTO nanophaseRoleDTO) {
        return iNanophaseRoleService.getRolePage(nanophaseRoleDTO);
    }

    /**
     * 批量新增角色信息
     *
     * @param roleDTOS
     * @return R
     */
    @WriteDB
    @WebLog("批量新增角色业务")
    @PostMapping("/insert")
    public R insertBatchRole(@RequestBody List<NanophaseRoleDTO> roleDTOS) {
        return iNanophaseRoleService.insertBatchRole(roleDTOS);
    }

    /**
     * 批量删除角色
     *
     * @param roleDTOS
     * @return
     */
    @WriteDB
    @WebLog(value = "批量删除角色业务（逻辑删除）")
    @PostMapping("/delete")
    public R deleteBatchRole(@RequestBody List<NanophaseRoleDTO> roleDTOS) {
        return iNanophaseRoleService.deleteBatchRole(roleDTOS);
    }

    /**
     * update
     *
     * @param nanophaseRoleDTO
     * @return
     */
    @WriteDB
    @WebLog(value = "修改role信息")
    @PostMapping("/update")
    public R updateRole(@RequestBody NanophaseRoleDTO nanophaseRoleDTO) {
        return iNanophaseRoleService.updateRole(nanophaseRoleDTO);
    }
}
