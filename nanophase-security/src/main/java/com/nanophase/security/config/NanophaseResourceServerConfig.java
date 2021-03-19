//package com.nanophase.security.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
///**
// * @author zhj
// * @since 2021-03-19
// * @apiNote 资源服务器
// */
//@Configuration
//@EnableResourceServer
//public class NanophaseResourceServerConfig extends ResourceServerConfigurerAdapter {
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("hello/oauth2/api/**").authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/oauth2/api/read/**").access("#oauth2.hasScope('all')")
//                .antMatchers(HttpMethod.GET, "/oauth2/api/write/**").access("#oauth2.hasScope('all')")
//        ;
//
//    }
//}
