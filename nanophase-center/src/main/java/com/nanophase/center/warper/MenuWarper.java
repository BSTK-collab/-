package com.nanophase.center.warper;

import com.nanophase.center.entity.NanophaseMenu;
import com.nanophase.common.dto.NanophaseMenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

/**
 * @author zhj
 * @since 2021-03-17
 * @apiNote 菜单实体类转换类
 */
@Mapper(componentModel = "spring")
@MapperConfig
public interface MenuWarper extends BaseWarper<NanophaseMenu, NanophaseMenuDTO> {
    MenuWarper INSTANCE = Mappers.getMapper(MenuWarper.class);
}
