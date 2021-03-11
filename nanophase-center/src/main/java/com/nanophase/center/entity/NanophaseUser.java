package com.nanophase.center.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * user 实体类
 * </p>
 *
 * @author zhj
 * @since 2021-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("nanophase_user")
public class NanophaseUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
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
    private Boolean userDeleted;

    /**
     * 是否禁用（0，未禁用；1，已禁用）
     * 默认0
     */
    private Boolean userStatus;

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
}
