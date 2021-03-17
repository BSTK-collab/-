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
    @WebLog
    @ReadDB
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
    @WebLog
    @PostMapping("/insert")
    public R insertBatchRole(@RequestBody List<NanophaseRoleDTO> roleDTOS) {
        return iNanophaseRoleService.insertBatchRole(roleDTOS);
    }
}
