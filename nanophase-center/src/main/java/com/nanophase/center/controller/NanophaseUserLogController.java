package com.nanophase.center.controller;

import com.nanophase.center.service.INanophaseUserLogService;
import com.nanophase.common.annotation.WebLog;
import com.nanophase.common.annotation.WriteDB;
import com.nanophase.common.dto.NanophaseUserLogDTO;
import com.nanophase.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户日志
 * </p>
 *
 * @author zhj
 * @since 2021-03-09
 */
@RestController
@RequestMapping("/nanophase-user-log")
public class NanophaseUserLogController {

    @Autowired
    private INanophaseUserLogService iNanophaseUserLogService;

    /**
     * 保存用户日志信息
     *
     * @param nanophaseUserLogDTO
     * @return R
     */
    @WebLog
    @WriteDB
    @RequestMapping("/insert")
    public R insert(@RequestBody NanophaseUserLogDTO nanophaseUserLogDTO) {
        return iNanophaseUserLogService.insert(nanophaseUserLogDTO);
    }
}
