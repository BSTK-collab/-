package com.nanophase.common.dto;

import com.nanophase.common.dto.page.PageDTO;
import lombok.Data;

import java.util.List;

/**
 * @author zhj
 * @date 2021-03-09
 */
@Data
public class NanophaseUserDTO extends PageDTO {

    /**
     * 主键ID
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户登录密码
     */
    private String userPassword;

    /**
     * 用户电话号码
     */
    private String userPhone;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 用户性别
     */
    private String userGender;

    /**
     * 逻辑删除标志（0，未删除；1，已删除）
     * 默认0
     */
    private Integer userDeleted;

    /**
     * 是否禁用（0，未禁用；1，已禁用）
     * 默认0
     */
    private Integer userStatus;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 所在区域
     */
    private String userRegion;

    /**
     * 备注信息
     */
    private String userRemark;

    /**
     * 用户的权限信息
     */
    private List<NanophaseRoleDTO> roles;
}
