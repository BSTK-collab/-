package com.nanophase.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nanophase.center.entity.NanophaseRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色与菜单中间表 Mapper 接口
 * </p>
 *
 * @author zhj
 * @since 2021-03-11
 */
@Mapper
public interface NanophaseRoleMenuMapper extends BaseMapper<NanophaseRoleMenu> {

}
