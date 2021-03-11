package com.nanophase.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nanophase.center.entity.NanophaseUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户与角色中间表 Mapper 接口
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
@Mapper
public interface NanophaseUserRoleMapper extends BaseMapper<NanophaseUserRole> {

}
