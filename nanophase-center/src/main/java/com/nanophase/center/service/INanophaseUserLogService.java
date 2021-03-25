package com.nanophase.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanophase.center.entity.NanophaseUserLog;
import com.nanophase.common.dto.NanophaseUserLogDTO;
import com.nanophase.common.util.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhj
 * @since 2021-03-09
 */
public interface INanophaseUserLogService extends IService<NanophaseUserLog> {

    /**
     * 保存用户日志信息
     *
     * @param nanophaseUserLogDTO
     * @return R
     */
    R insert(NanophaseUserLogDTO nanophaseUserLogDTO);
}
