package com.nanophase.center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanophase.center.entity.NanophaseSystemLog;
import com.nanophase.center.mapper.NanophaseSystemLogMapper;
import com.nanophase.center.service.INanophaseSystemLogService;
import com.nanophase.center.warper.NanophaseSystemLogWarper;
import com.nanophase.common.dto.NanophaseSystemLogDTO;
import com.nanophase.common.handler.NanophaseException;
import com.nanophase.common.util.R;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author zhj
 * @since 2021-03-29
 */
@Service
public class NanophaseSystemLogServiceImpl extends ServiceImpl<NanophaseSystemLogMapper, NanophaseSystemLog> implements INanophaseSystemLogService {

    /**
     * 新增日志
     *
     * @param nanophaseSystemLogDTO
     * @return
     */
    @Override
    public R insert(NanophaseSystemLogDTO nanophaseSystemLogDTO) {
        NanophaseSystemLog nanophaseSystemLog = NanophaseSystemLogWarper.INSTANCE.targetToSource(nanophaseSystemLogDTO);
        boolean save = this.save(nanophaseSystemLog);
        if (!save) {
            throw new NanophaseException("系统日志保存异常");
        }
        return null;
    }
}
