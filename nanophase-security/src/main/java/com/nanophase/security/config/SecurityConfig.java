package com.nanophase.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhj
 * @date 2021-03-10
 * security默认使用StrictHttpFirewall限制用户请求，uri不能包含//, ./, / ../,  /.等（分号，斜杠，反斜杠，百分号等）
 * 用户的用户名不能包含非法字符，是因为; -- %等非法字符等在请求参数中被禁用
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and().logout().permitAll()
                .and().csrf().disable().authorizeRequests();

//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                // 配置ip白名单
//                .antMatchers("nanophase-user/login")
//                .hasIpAddress("127.0.0.1")
//                .and()
//                .formLogin()
//                // 如果访问的时登录页  返回指定地址
//                .defaultSuccessUrl("指定地址",true)
//                // 登录成功处理器
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        System.out.println("登录成功");
//                        // 可以根据权限跳转至不同的页面
//                        request.getSession().getAttribute("");
//                        request.getRequestDispatcher("").forward(request,response);
//                    }
//                })
//                // 错误页
//                .failureUrl("")
//                .and()
//                .logout()
//                // 退出处理器 可以 .多个
//                .addLogoutHandler(new LogoutHandler() {
//                    @Override
//                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//                        System.out.println("退出");
//                    }
//                });
    }
}
