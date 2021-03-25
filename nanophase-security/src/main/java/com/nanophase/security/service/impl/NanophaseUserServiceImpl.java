package com.nanophase.security.service.impl;

import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.handler.NanophaseException;
import com.nanophase.feign.center.CenterApi;
import com.nanophase.security.entity.SecurityUser;
import com.nanophase.security.service.NanophaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zhj
 * @since 2021-03-25
 * @apiNote user-security相关业务实现类
 */
@Service
public class NanophaseUserServiceImpl implements NanophaseUserService {

    @Autowired
    private CenterApi centerApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NanophaseUserDTO nanophaseUserDTO = centerApi.selectUserByName(username);
        if (null == nanophaseUserDTO) {
            throw new UsernameNotFoundException("不存在该用户");
        }
        if (nanophaseUserDTO.getUserStatus() == 1) {
            throw new NanophaseException("账号已被禁用");
        }
        return new SecurityUser(nanophaseUserDTO);
    }

//    @Autowired
//    private AuthenticationManager authenticationManager;
//    /**
//     * 加载loadUserByUserName方法
//     *
//     * @param user 用户信息
//     * @return R
//     */
//    @Override
//    public R loadUserByUsername(NanophaseUserDTO user) {
//        try{
//            String[] roles = (String[]) user.getRoles().toArray();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword(),
//                    AuthorityUtils.createAuthorityList(roles)));
//            return R.success();
//        }catch (Exception e){
//            e.printStackTrace();
//            return R.error();
//        }
//    }
}
