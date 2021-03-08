package com.nanophase.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nanophase.center.entity.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelloMapper extends BaseMapper<Test> {
}
