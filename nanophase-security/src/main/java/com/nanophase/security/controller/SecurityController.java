package com.nanophase.security.controller;

import com.nanophase.common.dto.NanophaseUserDTO;
import com.nanophase.common.util.R;
import com.nanophase.security.service.NanophaseUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityController {

    /**
     * 注入时如果没有认证服务器会启动异常
     */
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private NanophaseUserService nanophaseUserService;

    /**
     * 方法进行前进行权限检查 只有admin用户才能执行该接口
     * 注解@PreAuthorize 其底层依然使用了hasAnyRole方法,securityConfig开启注解支持即可生效 prePostEnabled = true
     * PreAuthorize("hasAnyRole('admin','user','client')") 或者关系 包含一个即可
     * PreAuthorize("hasRole('admin') AND hasRole('user')") 并且关系
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public String hello() {
        return "hello";
    }

    /**
     * Secured({"ROLE_user"})必须具备user角色才可以访问，但是必须加上ROLE_前缀 并且开启注解支持 securedEnabled = true
     * PostAuthorize("returnObject = 1") Spring EL 表达式 根据返回值判断是否有权限
     * 返回结果可以是对象，可以是其他类型，表达式为true时，正常返回； 反之 返回403
     *
     * @return
     */
    @GetMapping("/get")
//    @Secured({"ROLE_user"})
    @PostAuthorize("returnObject = 1")
    public String hello1() {
        // Security封装 可以在任意的位置获取user信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userName = authentication.getPrincipal();
        log.info(userName.toString());
        log.info(authentication.getAuthorities().toString());
        return "hello user";
    }

    @GetMapping("/oauth2/api/read/1")
    public Authentication read() {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("read");

        return authentication;
    }

    @GetMapping("/oauth2/api/write/1")
    public Authentication write() {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("write");
        return authentication;
    }

    /**
     * 加载loadUserByUserName方法
     *
     * @param user 用户信息
     * @return R
     */
//    @PostMapping("/loadUserByUsername")
//    public R loadUserByUsername(@RequestBody NanophaseUserDTO user) {
//        return nanophaseUserService.loadUserByUsername(user);
//    }
}
