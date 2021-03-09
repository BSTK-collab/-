package com.nanophase.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanophase.center.entity.NanophaseUser;
import com.nanophase.center.mapper.NanophaseUserMapper;
import com.nanophase.center.service.INanophaseUserService;
import com.nanophase.common.DTO.NanophaseUserDTO;
import com.nanophase.common.handler.NanophaseException;
import com.nanophase.common.util.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhj
 * @since 2021-03-08
 */
@Service
public class NanophaseUserServiceImpl extends ServiceImpl<NanophaseUserMapper, NanophaseUser> implements INanophaseUserService {

    /**
     * 用户注册
     *
     * @param nanophaseUser
     * @return R
     */
    @Override
    public R register(NanophaseUser nanophaseUser) {
        verifyRegisterParam(nanophaseUser);
        List<NanophaseUser> nanophaseUsers = this.list(new QueryWrapper<NanophaseUser>()
                .eq("user_email", nanophaseUser.getUserEmail())
                .eq("user_deleted", 0));
        // email作为登录账号 不能重复注册
        if (null != nanophaseUsers && nanophaseUsers.size() > 0) {
            throw new NanophaseException("This email has been register");
        }
        // 密码加密
        String encode = new BCryptPasswordEncoder().encode(nanophaseUser.getUserPassword());
        nanophaseUser.setUserPassword(encode);
        return R.success().put("data", this.save(nanophaseUser));
    }

    /**
     * 用户登录
     *
     * @param nanophaseUserDTO
     * @return R
     */
    @Override
    public R login(NanophaseUserDTO nanophaseUserDTO) {
        verifyLoginParam(nanophaseUserDTO);
        return R.success();
    }

    /**
     * 校验用户登录参数
     *
     * @param nanophaseUserDTO
     */
    private void verifyLoginParam(NanophaseUserDTO nanophaseUserDTO) {
        if (StringUtils.isBlank(nanophaseUserDTO.getEmail())) {
            throw new NanophaseException("Email cannot be empty");
        }
        if (StringUtils.isBlank(nanophaseUserDTO.getPassword())) {
            throw new NanophaseException("Password cannot be empty");
        }
        // 校验用户名密码是否正确
        NanophaseUser nanophaseUser = this.getOne(new QueryWrapper<NanophaseUser>()
                .eq("user_email", nanophaseUserDTO.getEmail())
                .eq("user_deleted", 0));
        if (null == nanophaseUser) {
            throw new NanophaseException("This email was not found, please register");
        }
        boolean matches = new BCryptPasswordEncoder().matches(nanophaseUserDTO.getPassword(), nanophaseUser.getUserPassword());
        if (!matches) {
            // 用户名或密码错误
            throw new NanophaseException("Email or password wrong");
        }
    }

    /**
     * 校验用户必传参数 TODO 暂定使用email登录
     *
     * @param nanophaseUser
     */
    private void verifyRegisterParam(NanophaseUser nanophaseUser) {
        if (StringUtils.isBlank(nanophaseUser.getUserPassword())) {
            throw new NanophaseException("password cannot be empty, please check");
        }

        if (StringUtils.isBlank(nanophaseUser.getNickName())) {
            throw new NanophaseException("nick name cannot be empty, please check");
        }

        if (StringUtils.isBlank(nanophaseUser.getUserEmail())) {
            throw new NanophaseException("email cannot be empty, please check");
        }
    }
}
