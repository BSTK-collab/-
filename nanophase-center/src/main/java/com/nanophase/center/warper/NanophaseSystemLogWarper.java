package com.nanophase.center.warper;

import com.nanophase.center.entity.NanophaseSystemLog;
import com.nanophase.common.dto.NanophaseSystemLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

/**
 * @author zhj
 * @apiNote 实体类转换
 * @since 2021-03-29
 */
@Mapper(componentModel = "spring")
@MapperConfig
public interface NanophaseSystemLogWarper extends BaseWarper<NanophaseSystemLog, NanophaseSystemLogDTO> {
    NanophaseSystemLogWarper INSTANCE = Mappers.getMapper(NanophaseSystemLogWarper.class);
}
