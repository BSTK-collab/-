package com.nanophase.center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanophase.center.entity.NanophaseUserLog;
import com.nanophase.center.mapper.NanophaseUserLogMapper;
import com.nanophase.center.service.INanophaseUserLogService;
import com.nanophase.center.warper.UserLogWarper;
import com.nanophase.common.dto.NanophaseUserLogDTO;
import com.nanophase.common.handler.NanophaseException;
import com.nanophase.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhj
 * @since 2021-03-09
 */
@Slf4j
@Service
public class NanophaseUserLogServiceImpl extends ServiceImpl<NanophaseUserLogMapper, NanophaseUserLog> implements INanophaseUserLogService {

    /**
     * 保存用户日志信息
     *
     * @param nanophaseUserLogDTO
     * @return R
     */
    @Override
    public R insert(NanophaseUserLogDTO nanophaseUserLogDTO) {
        NanophaseUserLog nanophaseUserLog = UserLogWarper.INSTANCE.targetToSource(nanophaseUserLogDTO);
        boolean save = this.save(nanophaseUserLog);
        if (!save) {
            log.error("用户日志信息保存异常,详情:{}", nanophaseUserLogDTO.toString());
            throw new NanophaseException("用户日志信息保存异常");
        }
        return R.success();
    }
}
