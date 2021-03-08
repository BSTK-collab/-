package com.nanophase.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanophase.center.entity.NanophaseUser;
import com.nanophase.center.mapper.NanophaseUserMapper;
import com.nanophase.center.service.INanophaseUserService;
import com.nanophase.common.handler.NanophaseException;
import com.nanophase.common.util.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
        verifyUserParam(nanophaseUser);
        List<NanophaseUser> nanophaseUsers = this.list(new QueryWrapper<NanophaseUser>()
                .eq("user_email", nanophaseUser.getUserEmail())
                .eq("user_deleted", 0));
        if (null != nanophaseUsers && nanophaseUsers.size() > 0){
            throw new NanophaseException("This email has been register");
        }
        // 密码加密
        String encode = new BCryptPasswordEncoder().encode(nanophaseUser.getUserPassword());
        nanophaseUser.setUserPassword(encode);
        return R.success().put("data",this.save(nanophaseUser));
    }

    /**
     * 校验用户必传参数 TODO 暂定使用email登录
     *
     * @param nanophaseUser
     */
    private void verifyUserParam(NanophaseUser nanophaseUser) {
        if (StringUtils.isBlank(nanophaseUser.getUserPassword())){
            throw new NanophaseException("password cannot be empty, please check");
        }

        if (StringUtils.isBlank(nanophaseUser.getNickName())){
            throw new NanophaseException("nick name cannot be empty, please check");
        }

        if (StringUtils.isBlank(nanophaseUser.getUserEmail())){
            throw new NanophaseException("email cannot be empty, please check");
        }
    }
}
