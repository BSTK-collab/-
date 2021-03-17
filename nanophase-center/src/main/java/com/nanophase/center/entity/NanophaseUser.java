package com.nanophase.center.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class NanophaseUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 真实姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户邮箱
     */
    @TableField("user_email")
    private String userEmail;

    /**
     * 用户登录密码
     */
    @TableField("user_password")
    private String userPassword;

    /**
     * 用户电话号码
     */
    @TableField("user_phone")
    private String userPhone;

    /**
     * 用户头像
     */
    @TableField("user_icon")
    private String userIcon;

    /**
     * 用户性别
     */
    @TableField("user_gender")
    private String userGender;

    /**
     * 逻辑删除标志（0，未删除；1，已删除）
     * 默认0
     */
    @TableField("user_deleted")
    private Integer userDeleted;

    /**
     * 是否禁用（0，未禁用；1，已禁用）
     * 默认0
     */
    @TableField("user_status")
    private Integer userStatus;

    /**
     * 用户类型
     */
    @TableField("user_type")
    private String userType;

    /**
     * 所在区域
     */
    @TableField("user_region")
    private String userRegion;

    /**
     * 备注信息
     */
    @TableField("user_remark")
    private String userRemark;
}
