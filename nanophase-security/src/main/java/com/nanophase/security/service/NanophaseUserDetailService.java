package com.nanophase.security.service;

import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.constant.CenterConstant;
import com.nanophase.common.enums.ErrorCodeEnum;
import com.nanophase.feign.center.CenterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Service;

@Service
public class NanophaseUserDetailService implements UserDetailsService {

    @Autowired
    private CenterApi centerApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 远程调用获取用户信息
        NanophaseUserDTO nanophaseUserDTO = centerApi.selectUserByName(username);
        if (null == nanophaseUserDTO) {
            // security的异常
            throw new UsernameNotFoundException(ErrorCodeEnum.USERNAME_PASSWORD_ERROR.getMsg());
        }

        if (nanophaseUserDTO.getUserStatus().equals(CenterConstant.User.USER_STATUS_1)) {
            // 账号被禁用 TODO 在登录时校验；在设置为禁用时，直接将token置为无效
            throw new UserDeniedAuthorizationException(ErrorCodeEnum.USER_DISABLED.getMsg());
        }
        return new org.springframework.security.core.userdetails.User(username,nanophaseUserDTO.getUserPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
