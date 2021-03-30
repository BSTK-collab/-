package com.nanophase.security.handler;

import com.nanophase.common.dto.NanophaseUserLogDTO;
import com.nanophase.common.feign.center.CenterApi;
import com.nanophase.common.manager.AsyncManager;
import com.nanophase.common.util.NetworkUtil;
import com.nanophase.security.entity.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhj
 * @apiNote 自定义登录失败处理器 返回自定义的json数据
 * @since 2021-03-12
 */
@Slf4j
@Component
public class NanophaseAuthenticationFailHandler implements AuthenticationFailureHandler {

    @Autowired
    private CenterApi centerApi;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.error("登录失败");
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("登录失败");
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        try {
            // 异步调用远程接口 存储用户登录日志
            AsyncManager.getInstance().execute(() -> {
                SecurityUser user = new SecurityUser();
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (null != principal) {
                    user = (SecurityUser) principal;
                }
                NanophaseUserLogDTO userLogDTO = new NanophaseUserLogDTO();
                if (exception instanceof UsernameNotFoundException) {
                    userLogDTO.setEMessage("不存在该用户");
                } else {
                    userLogDTO.setEMessage(exception.getMessage());
                }
                userLogDTO.setNanophaseUserId(user.getUserId());
                userLogDTO.setIpAddr(NetworkUtil.getIpAddress(request));
                userLogDTO.setLoginStatus(0);
                userLogDTO.setNanophaseUserEmail(user.getUsername());

                centerApi.insertUserLoginInfo(userLogDTO);
            }, null);
        } catch (Exception e) {
            log.error("登录失败处理器，异步保存用户登录信息异常,e:{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
