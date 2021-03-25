package com.nanophase.security.filter;


import com.nanophase.common.handler.NanophaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zhj
 * @date 2021-03-10
 * @apiNote 图形验证码过滤器
 * Filter ---> tomcat级别过滤器 执行优先于Spring
 */
@Slf4j
//@Order(1)
//@WebFilter
public class GraphCodeFilter implements Filter {

    /**
     * 执行顺序:执行chain.doFilter之前的代码 执行完业务（service）后执行chain.doFilter之后的代码
     * 如果有多个过滤器(过滤器链) FilterA impl Filter; FilterB impl Filter; FilterC impl Filter则按照名称排序执行
     * (过滤器链)chain.doFilter满足先进后出的原则
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
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
            } else {
                throw new NanophaseException("验证图形验证码发生异常");
            }
        }
        chain.doFilter(request, response);
    }
}
