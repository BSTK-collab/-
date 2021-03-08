package com.nanophase.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanophase.center.entity.NanophaseUser;
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
}
