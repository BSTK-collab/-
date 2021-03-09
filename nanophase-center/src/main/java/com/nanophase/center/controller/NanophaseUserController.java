package com.nanophase.center.controller;

import com.nanophase.center.entity.NanophaseUser;
import com.nanophase.center.service.INanophaseUserService;
import com.nanophase.common.DTO.NanophaseUserDTO;
import com.nanophase.common.annotation.ReadDB;
import com.nanophase.common.annotation.WriteDB;
import com.nanophase.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-03-08
 */
@RestController
@RequestMapping("/nanophase-user")
public class NanophaseUserController {

    @Autowired
    private INanophaseUserService iNanophaseUserService;

    /**
     * 用户注册
     *
     * @param nanophaseUser
     * @return R
     */
    @WriteDB
    @PostMapping("/register")
    public R register(@RequestBody NanophaseUser nanophaseUser) {
        return iNanophaseUserService.register(nanophaseUser);
    }

    /**
     * 用户登录 Token方式
     *
     * @param nanophaseUserDTO
     * @return R
     */
    @ReadDB
    @PostMapping("/login")
    public R login(@RequestBody NanophaseUserDTO nanophaseUserDTO) {
        return iNanophaseUserService.login(nanophaseUserDTO);
    }
}
