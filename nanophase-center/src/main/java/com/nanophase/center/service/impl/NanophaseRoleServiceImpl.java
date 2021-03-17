package com.nanophase.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanophase.center.entity.NanophaseRole;
import com.nanophase.center.mapper.NanophaseRoleMapper;
import com.nanophase.center.service.INanophaseRoleService;
import com.nanophase.center.warper.RoleWarper;
import com.nanophase.common.dto.NanophaseRoleDTO;
import com.nanophase.common.util.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
@Service
public class NanophaseRoleServiceImpl extends ServiceImpl<NanophaseRoleMapper, NanophaseRole> implements INanophaseRoleService {

    /**
     * 分页查询角色信息
     *
     * @param nanophaseRoleDTO
     * @return R
     */
    @Override
    public R getRolePage(NanophaseRoleDTO nanophaseRoleDTO) {
        Page<NanophaseRole> page = new Page<>(nanophaseRoleDTO.getCurrent(), nanophaseRoleDTO.getSize());
        page.setOrders(nanophaseRoleDTO.getOrderItems());
        QueryWrapper<NanophaseRole> queryWrapper = new QueryWrapper<>();
        // 是否禁用
        queryWrapper.eq(nanophaseRoleDTO.getRoleStatus() != null, "role_status", nanophaseRoleDTO.getRoleStatus());

        // 角色名称
        queryWrapper.eq(StringUtils.isNotBlank(nanophaseRoleDTO.getRoleName()), "role_name", nanophaseRoleDTO.getRoleName());

        // 角色编码
        queryWrapper.eq(StringUtils.isNotBlank(nanophaseRoleDTO.getRoleCode()), "role_code", nanophaseRoleDTO.getRoleCode());

        // 是否删除
        queryWrapper.eq(nanophaseRoleDTO.getRoleDelete() != null, "role_delete", nanophaseRoleDTO.getRoleDelete());
        return R.success().put("data", this.page(page, queryWrapper));
    }

    /**
     * 批量新增角色信息
     *
     * @param roleDTOS
     * @return R
     */
    @Override
    public R insertBatchRole(List<NanophaseRoleDTO> roleDTOS) {
        List<NanophaseRole> roles = new ArrayList<>();
        for (NanophaseRoleDTO roleDTO : roleDTOS) {
            if (StringUtils.isBlank(roleDTO.getRoleName())) {
                return R.error("Please fill in the role name");
            }
            NanophaseRole nanophaseRole = RoleWarper.INSTANCE.targetToSource(roleDTO);
            // 排序字段
            if (null == nanophaseRole.getRoleSort()) {
                nanophaseRole.setRoleSort(0);
            }
            roles.add(nanophaseRole);
        }
        if (roles.size() > 0) {
            return R.success().put("dara", this.saveBatch(roles));
        }
        return R.success();
    }
}
