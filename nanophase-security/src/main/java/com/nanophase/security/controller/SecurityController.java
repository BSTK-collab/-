package com.nanophase.security.controller;

import com.nanophase.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class SecurityController {

//    @Autowired
//    private TokenEndpoint tokenEndpoint;

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
    @GetMapping("/get")
    @Secured({"ROLE_user"})
    public String hello1() {
        return "hello user";
    }

    /**
     * 构造token信息
     *
     * @param map client信息与用户数据
     * @return R
//     */
//    @PostMapping("/token")
//    public R getToken(Principal principal, @RequestParam Map<String, String> map) throws HttpRequestMethodNotSupportedException {
//        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, map).getBody();
//        return R.success().put("data",oAuth2AccessToken);
//    }
}
