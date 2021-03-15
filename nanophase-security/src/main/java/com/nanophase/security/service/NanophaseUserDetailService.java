//package com.nanophase.security.service;
//
//import com.nanophase.common.DTO.NanophaseUserDTO;
//import com.nanophase.common.constant.CenterConstant;
//import com.nanophase.common.enums.ErrorCodeEnum;
//import com.nanophase.feign.center.CenterApi;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NanophaseUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private CenterApi centerApi;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 远程调用获取用户信息
//        NanophaseUserDTO nanophaseUserDTO = centerApi.selectUserByName(username);
//        if (null == nanophaseUserDTO) {
//            // security的异常
//            throw new UsernameNotFoundException(ErrorCodeEnum.USERNAME_PASSWORD_ERROR.getMsg());
//        }
//
//        if (nanophaseUserDTO.getUserStatus().equals(CenterConstant.UserStatus.USER_STATUS_1)) {
//            // 账号被禁用
//            throw new UserDeniedAuthorizationException(ErrorCodeEnum.USER_DISABLED.getMsg());
//        }
//        return new User(username,nanophaseUserDTO.getPassword(),
//                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
//    }
//}
