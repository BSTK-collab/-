package com.nanophase.center.warper;

import com.nanophase.center.entity.NanophaseUserLog;
import com.nanophase.common.dto.NanophaseUserLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

/**
 * @author zhj
 * @apiNote 用户日志信息实体类转换类
 * @since 2021-03-25
 */
@Mapper(componentModel = "spring")
@MapperConfig
public interface UserLogWarper extends BaseWarper<NanophaseUserLog, NanophaseUserLogDTO> {
    UserLogWarper INSTANCE = Mappers.getMapper(UserLogWarper.class);
}
