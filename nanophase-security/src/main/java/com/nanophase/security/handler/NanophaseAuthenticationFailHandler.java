package com.nanophase.security.handler;

import com.nanophase.common.dto.NanophaseUserLogDTO;
import com.nanophase.common.manager.AsyncManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhj
 * @apiNote 自定义登录失败处理器 返回自定义的json数据
 * 可以选择实现AuthenticationFailureHandler 继承关系
 * @since 2021-03-12
 */
@Slf4j
@Component
public class NanophaseAuthenticationFailHandler implements AuthenticationFailureHandler {

    // TODO: 2021/3/25 会覆盖抛出的具体异常 需要修改
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("登录失败");
        response.getWriter().write("登录失败");
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
//        try {
//            // 异步调用远程接口 存储用户登录日志
//            AsyncManager.getInstance().execute(() -> {
//                NanophaseUserLogDTO userLogDTO = new NanophaseUserLogDTO();
//                userLogDTO.setNanophaseUserId(user.getUserId());
//                userLogDTO.setIpAddr(ipAddress);
//                userLogDTO.setLoginStatus(0);
//                userLogDTO.setNanophaseUserEmail(user.getUsername());
//                userLogDTO.setEMessage("登录失败");
//                centerApi.insertUserLoginInfo(userLogDTO);
//            }, null);
//        } catch (Exception e) {
//            log.error("登录失败处理器，异步保存用户登录信息异常,e:{}", e.getMessage());
//            e.printStackTrace();
//        }
        this.onAuthenticationFailure(request, response, exception);
    }
}
