package com.nanophase.center.warper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;

import java.util.List;

/**
 * @author zhj
 * @since 2021-03-16
 * @apiNote 实体类基本转换
 * @param <SOURCE>
 * @param <TARGET>
 */
@MapperConfig
public interface BaseWarper<SOURCE,TARGET> {

    @InheritInverseConfiguration(name = "sourceToTarget")
    TARGET sourceToTarget(SOURCE var);

    @InheritInverseConfiguration(name = "sourceToTarget")
    List<TARGET> sourceToTarget(List<SOURCE> var);

    @InheritInverseConfiguration(name = "targetToSource")
    SOURCE targetToSource(TARGET var);

    @InheritInverseConfiguration(name = "targetToSource")
    List<SOURCE> targetToSource(List<TARGET> var);
}
