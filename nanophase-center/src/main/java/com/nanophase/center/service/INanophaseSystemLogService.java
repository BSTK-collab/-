package com.nanophase.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanophase.center.entity.NanophaseSystemLog;
import com.nanophase.common.dto.NanophaseSystemLogDTO;
import com.nanophase.common.util.R;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author zhj
 * @since 2021-03-29
 */
public interface INanophaseSystemLogService extends IService<NanophaseSystemLog> {

    /**
     * 新增日志
     *
     * @param nanophaseSystemLogDTO
     * @return
     */
    R insert(NanophaseSystemLogDTO nanophaseSystemLogDTO);
}
