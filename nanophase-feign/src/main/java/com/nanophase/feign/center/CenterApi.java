package com.nanophase.feign.center;

import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.dto.NanophaseUserLogDTO;
import com.nanophase.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhj
 * @apiNote 远程调用业务---核心业务模块接口
 * @since 2021-03-12
 */
@FeignClient("nanophase-center")
public interface CenterApi {

    /**
     * 根据用户登录账号获取用户信息
     *
     * @param username
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/nanophase-user/selectUserByName")
    NanophaseUserDTO selectUserByName(@RequestParam String username);

    /**
     * 保存用户登录日志信息
     *
     * @param userLogDTO
     */
    @RequestMapping(method = RequestMethod.POST, value = "/nanophase-user-log/insert")
    void insertUserLoginInfo(NanophaseUserLogDTO userLogDTO);
}
