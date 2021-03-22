package com.nanophase.security.service.impl;

import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.util.R;
import com.nanophase.security.service.NanophaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class NanophaseUserServiceImpl implements NanophaseUserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 加载loadUserByUserName方法
     *
     * @param user 用户信息
     * @return R
     */
    @Override
    public R loadUserByUsername(NanophaseUserDTO user) {
        try{
            String[] roles = (String[]) user.getRoles().toArray();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword(),
                    AuthorityUtils.createAuthorityList(roles)));
            return R.success();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
