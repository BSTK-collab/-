package com.nanophase.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


/**
 * @author zhj
 * @date 2021-03-10
 * @apiNote 认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    /**
     * 配置客户端 可以通过密码或者授权码方式获取令牌
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // client ID
                .withClient("123456")
                // 加密的密码
                .secret(passwordEncoder.encode("123456"))
                // 有效时间2小时
                .accessTokenValiditySeconds(120)
                // 注册验证方式(这里只注册了验证码方式，密码方式，刷新令牌方式)
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                // 所有领域全部生效
                .scopes("all")
                // 跳转链接
                .redirectUris("www.baidu.com")
        ;
    }

    /**
     * Token管理
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(new InMemoryTokenStore())
//                .accessTokenConverter(accessTokenConverter())
//                .authenticationManager(authenticationManagerBean)
//                .userDetailsService(nanophaseUserDetailService)
//                .reuseRefreshTokens(false)
        endpoints.authenticationManager(authenticationManagerBean);
        ;
    }
}
