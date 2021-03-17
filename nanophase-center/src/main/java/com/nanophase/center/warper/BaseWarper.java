package com.nanophase.center.warper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhj
 * @since 2021-03-16
 * @apiNote 实体类默认转换方法
 * @param <SOURCE>
 * @param <TARGET>
 */
public interface BaseWarper<SOURCE,TARGET> {

    @InheritConfiguration
    TARGET sourceToTarget(SOURCE var);

    @InheritConfiguration
    List<TARGET> sourceToTarget(List<SOURCE> var);

    @InheritInverseConfiguration
    SOURCE targetToSource(TARGET var);

    @InheritInverseConfiguration
    List<SOURCE> targetToSource(List<TARGET> var);
}
