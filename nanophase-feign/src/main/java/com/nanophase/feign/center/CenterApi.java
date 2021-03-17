package com.nanophase.feign.center;

import com.nanophase.common.dto.NanophaseUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhj
 * @since 2021-03-12
 * @apiNote 远程调用业务---核心业务模块接口
 */
@FeignClient("nanophase-center")
public interface CenterApi {

    @RequestMapping(method = RequestMethod.POST,value = "/nanophase-user/selectUserByName")
    NanophaseUserDTO selectUserByName(@RequestParam String username);
}
