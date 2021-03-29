package com.nanophase.center.controller;

import com.nanophase.center.entity.NanophaseUser;
import com.nanophase.center.service.INanophaseUserService;
import com.nanophase.common.annotation.WebLog;
import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.annotation.ReadDB;
import com.nanophase.common.annotation.WriteDB;
import com.nanophase.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器 用户相关业务
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
     * TODO: 2021/3/29 已废弃 使用了spring-security内置的登录接口
     *
     * @param nanophaseUserDTO
     * @return R
     */
    @Deprecated
    @ReadDB
    @PostMapping("/login")
    public R login(@RequestBody NanophaseUserDTO nanophaseUserDTO, HttpServletRequest request) {
        return iNanophaseUserService.login(nanophaseUserDTO, request);
    }

    /**
     * 根据用户名查询用户密码
     *
     * @param username 登录账号 这里是email
     * @return
     */
    @ReadDB
    @PostMapping("/selectUserByName")
    public NanophaseUserDTO selectUserByName(@RequestParam String username) {
        return iNanophaseUserService.selectUserByName(username);
    }

    /**
     * 分页查询用户信息
     *
     * @param nanophaseUserDTO
     * @return R
     */
    @WebLog
    @ReadDB
    @PostMapping("/page")
    public R getUserPage(@RequestBody NanophaseUserDTO nanophaseUserDTO) {
        return iNanophaseUserService.getUserPage(nanophaseUserDTO);
    }

    /**
     * 修改用户信息
     *
     * @param userDTO
     * @return
     */
    @WriteDB
    @WebLog(value = "修改用户信息")
    @PostMapping("/update")
    public R updateUser(@RequestBody NanophaseUserDTO userDTO) {
        return iNanophaseUserService.updateUser(userDTO);
    }

    /**
     * 解禁用户 或者 禁用用户
     *
     * @param userDTO
     * @return
     */
//    @PreAuthorize("hasAnyAuthority('admin')")
    @WriteDB
    @WebLog(value = "解禁用户或禁用用户")
    @PostMapping("/updateUserStatus")
    public R updateUserStatus(@RequestBody NanophaseUserDTO userDTO) {
        return iNanophaseUserService.updateUserStatus(userDTO);
    }
}
