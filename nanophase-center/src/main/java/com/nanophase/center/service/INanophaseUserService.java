package com.nanophase.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanophase.center.entity.NanophaseUser;
import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.util.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhj
 * @since 2021-03-08
 */
public interface INanophaseUserService extends IService<NanophaseUser> {

    /**
     * 用户注册
     *
     * @param nanophaseUser
     * @return R
     */
    R register(NanophaseUser nanophaseUser);

    /**
     * 用户登录
     *
     * @param nanophaseUserDTO
     * @return R
     */
    R login(NanophaseUserDTO nanophaseUserDTO);

    /**
     * 根据用户名查询用户密码
     *
     * @param username 登录账号 这里是email
     * @return
     */
    NanophaseUserDTO selectUserByName(String username);

    /**
     * 分页查询用户信息
     *
     * @param nanophaseUserDTO
     * @return R
     */
    R getUserPage(NanophaseUserDTO nanophaseUserDTO);
}
