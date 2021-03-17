package com.nanophase.center.warper;

import com.nanophase.center.entity.NanophaseRole;
import com.nanophase.common.dto.NanophaseRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

/**
 * @author zhj
 * @since 2021-03-17
 * @apiNote 角色实体类转换类
 */
@Mapper(componentModel = "spring")
@MapperConfig
public interface RoleWarper extends BaseWarper<NanophaseRole,NanophaseRoleDTO> {
    RoleWarper INSTANCE = Mappers.getMapper(RoleWarper.class);

    @Override
    NanophaseRole targetToSource(NanophaseRoleDTO var);
}
