package com.nanophase.security.service;

import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.util.R;

public interface NanophaseUserService {

    /**
     * 加载loadUserByUserName方法
     *
     * @param user 用户信息
     * @return R
     */
    R loadUserByUsername(NanophaseUserDTO user);
}
