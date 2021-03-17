package com.nanophase.center.warper;

import com.nanophase.center.entity.NanophaseUser;
import com.nanophase.common.dto.NanophaseUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

/**
 * @author zhj
 * @since 2021-03-17
 * @apiNote 用户实体类转换类
 */
@Mapper(componentModel = "spring")
@MapperConfig
public interface UserWarper extends BaseWarper<NanophaseUser, NanophaseUserDTO> {
    UserWarper INSTANCE = Mappers.getMapper(UserWarper.class);
}
