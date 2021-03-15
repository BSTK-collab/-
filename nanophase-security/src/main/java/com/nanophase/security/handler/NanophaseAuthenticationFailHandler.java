package com.nanophase.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhj
 * @since 2021-03-12
 * @apiNote 自定义登录失败处理器 返回自定义的json数据
 * 可以选择实现AuthenticationFailureHandler 继承关系
 */
@Slf4j
@Component
public class NanophaseAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("登录失败");
        response.getWriter().write("login err");
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        super.onAuthenticationFailure(request, response, exception);
    }
}
