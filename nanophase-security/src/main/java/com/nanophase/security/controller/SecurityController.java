package com.nanophase.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SecurityController {

    /**
     * 方法进行前进行权限检查 只有admin用户才能执行该接口
     * 注解@PreAuthorize 其底层依然使用了hasAnyRole方法,securityConfig开启注解支持即可生效 prePostEnabled = true
     * PreAuthorize("hasAnyRole('admin','user','client')") 或者关系 包含一个即可
     * PreAuthorize("hasRole('admin') AND hasRole('user')") 并且关系
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public String hello() {
        return "hello";
    }

    /**
     * 该方法必须具备user角色才可以访问，但是必须加上ROLE_前缀 并且开启注解支持 securedEnabled = true
     *
     * @return
     */
    @GetMapping
    @Secured({"ROLE_user"})
    public String hello1() {
        return "hello user";
    }
}
