package com.nanophase.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanophase.center.entity.NanophaseUser;
import com.nanophase.center.entity.NanophaseUserLog;
import com.nanophase.center.mapper.NanophaseUserMapper;
import com.nanophase.center.service.INanophaseUserLogService;
import com.nanophase.center.service.INanophaseUserService;
import com.nanophase.common.DTO.NanophaseUserDTO;
import com.nanophase.common.constant.AuthConstant;
import com.nanophase.common.constant.CenterConstant;
import com.nanophase.common.enums.ErrorCodeEnum;
import com.nanophase.common.handler.NanophaseException;
import com.nanophase.common.util.R;
import com.nanophase.feign.security.SecurityApi;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private INanophaseUserLogService iNanophaseUserLogService;
    @Autowired
    private SecurityApi securityApi;

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
        NanophaseUser nanophaseUser = verifyLoginParam(nanophaseUserDTO);
        try {
            // 保存用户登录记录
            NanophaseUserLog nanophaseUserLog = new NanophaseUserLog();
            nanophaseUserLog.setNanophaseUserId(nanophaseUser.getUserId());
            nanophaseUserLog.setNanophaseUserEamil(nanophaseUser.getUserEmail());
            nanophaseUserLog.setCreateDate(LocalDateTime.now());
            boolean save = iNanophaseUserLogService.save(nanophaseUserLog);
            if (!save) {
                // 保存用户登录记录失败
                throw new NanophaseException("保存用户登录日志异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 远程调用security 获取token
        // 构造获取token所需参数
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.CLIENT_ID_CENTER);
        // TODO: 2021/3/12  密码加密问题 client_secret应该可以自定义名字
        params.put("client_secret",new BCryptPasswordEncoder().encode("123456"));
        // oauth使用的四种授权方式之一，密码式
        params.put("grant_type","password");
        params.put("username",nanophaseUserDTO.getEmail());
        params.put("password",nanophaseUser.getUserPassword());
        // 远程调用获取token
        R result = securityApi.getToken(params);
        if (result == null || result.get("code").equals(HttpStatus.SC_OK) || result.get("data") == null){
            return R.error("登录失败");
        }
        return result;
    }

    /**
     * 根据用户名查询用户密码
     *
     * @param username 登录账号 这里是email
     * @return
     */
    @Override
    public NanophaseUserDTO selectUserByName(String username) {
        NanophaseUser nanophaseUser = this.getOne(new QueryWrapper<NanophaseUser>().eq("user_email", username)
                .eq("user_deleted", 0).select("user_password", "user_status"));
        if (null != nanophaseUser) {
            NanophaseUserDTO nanophaseUserDTO = new NanophaseUserDTO();
            nanophaseUserDTO.setPassword(nanophaseUser.getUserPassword());
            nanophaseUserDTO.setUserStatus(nanophaseUser.getUserStatus());
            return nanophaseUserDTO;
        }
        return null;
    }

    /**
     * 校验用户登录参数
     *
     * @param nanophaseUserDTO
     */
    private NanophaseUser verifyLoginParam(NanophaseUserDTO nanophaseUserDTO) {
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
        // 账号被禁用
        if (nanophaseUser.getUserStatus().equals(CenterConstant.UserStatus.USER_STATUS_1)){
            throw new NanophaseException(ErrorCodeEnum.USER_DISABLED.getMsg());
        }
        boolean matches = new BCryptPasswordEncoder().matches(nanophaseUserDTO.getPassword(), nanophaseUser.getUserPassword());
        if (!matches) {
            // 用户名或密码错误
            throw new NanophaseException("Email or password wrong");
        }
        return nanophaseUser;
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
