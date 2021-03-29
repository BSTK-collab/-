package com.nanophase.center.controller;

import com.nanophase.center.entity.NanophaseSystemLog;
import com.nanophase.center.service.INanophaseSystemLogService;
import com.nanophase.common.annotation.WriteDB;
import com.nanophase.common.dto.NanophaseSystemLogDTO;
import com.nanophase.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统日志表 前端控制器
 * </p>
 *
 * @author zhj
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/nanophase-system-log")
public class NanophaseSystemLogController {

    @Autowired
    private INanophaseSystemLogService iNanophaseSystemLogService;

    /**
     * 新增日志
     *
     * @param nanophaseSystemLogDTO
     * @return
     */
    @WriteDB
    @PostMapping("/insert")
    public R insert(@RequestBody NanophaseSystemLogDTO nanophaseSystemLogDTO) {
        return iNanophaseSystemLogService.insert(nanophaseSystemLogDTO);
    }
}
