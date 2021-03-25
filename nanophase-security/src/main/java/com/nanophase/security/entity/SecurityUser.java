package com.nanophase.security.entity;

import com.nanophase.common.dto.NanophaseUserDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhj
 * @apiNote security-user实体类 spring-security-userDetail实体类的扩充
 * @since 2021-03-25
 */
@Data
public class SecurityUser implements UserDetails {

    private Long userId;

    private String username;

    private String password;

    /**
     * 是否被禁用（0，未禁用；1，已禁用）
     */
    private Integer userStatus;

    /**
     * 封装权限信息至security
     */
    private Collection<GrantedAuthority> nanophaseAuthorities;

    public SecurityUser(NanophaseUserDTO userDTO) {
        this.userId = userDTO.getUserId();
        this.userStatus = userDTO.getUserStatus();
        this.password = userDTO.getUserPassword();
        this.username = userDTO.getUserEmail();
        // 构造用户权限信息 保存入security框架
//        List<String> roles = userDTO.getRoles();
//        if (null != roles && roles.size() > 0) {
        this.nanophaseAuthorities = new ArrayList<>();
//            for (String role : roles) {
        nanophaseAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");

//            }
//        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.nanophaseAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.userStatus == 0;
    }
}
