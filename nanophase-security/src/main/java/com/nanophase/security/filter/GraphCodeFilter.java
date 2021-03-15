package com.nanophase.security.filter;


import com.nanophase.common.handler.NanophaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zhj
 * @date 2021-03-10
 * @apiNote 图形验证码过滤器
 * Filter ---> tomcat级别过滤器 执行优先于Spring
 */
@Slf4j
public class GraphCodeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        if (requestURI.equals("/nanophase-user/login") && httpServletRequest.getMethod().equalsIgnoreCase("post")) {
            // 获取提交信息
            String graphSessionKey = httpServletRequest.getSession().getAttribute("graph_session_key").toString();
            String graphCode = httpServletRequest.getParameter("graph_code");
            if (StringUtils.isBlank(graphCode)) {
                throw new NanophaseException("图形验证码不能为空");
            }
            if (graphSessionKey.equalsIgnoreCase(graphCode)) {
                log.info("验证通过");
                chain.doFilter(request, response);
            } else {
                throw new NanophaseException("验证图形验证码发生异常");
            }
        }
        chain.doFilter(request, response);
    }
}
