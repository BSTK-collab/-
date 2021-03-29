package com.nanophase.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanophase.center.entity.NanophaseRole;
import com.nanophase.common.dto.NanophaseRoleDTO;
import com.nanophase.common.util.R;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
public interface INanophaseRoleService extends IService<NanophaseRole> {

    /**
     * 分页查询角色信息
     *
     * @param nanophaseRoleDTO
     * @return R
     */
    R getRolePage(NanophaseRoleDTO nanophaseRoleDTO);

    /**
     * 批量新增角色信息
     *
     * @param roleDTOS
     * @return R
     */
    R insertBatchRole(List<NanophaseRoleDTO> roleDTOS);

    /**
     * 批量删除角色
     *
     * @param roleDTOS
     * @return
     */
    R deleteBatchRole(List<NanophaseRoleDTO> roleDTOS);

    /**
     * update
     *
     * @param nanophaseRoleDTO
     * @return
     */
    R updateRole(NanophaseRoleDTO nanophaseRoleDTO);
}
