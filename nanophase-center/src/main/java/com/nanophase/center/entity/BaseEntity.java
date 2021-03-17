package com.nanophase.center.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhj
 * @since 2021-03-08
 * @apiNote entity父类
 */
public class BaseEntity implements Serializable {

    @TableField(value = "create_user",fill = FieldFill.INSERT)
    private String createUser;

    @TableField(value = "create_user_name",fill = FieldFill.INSERT)
    private String createUserName;

    @TableField(value = "create_date",fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @TableField(value = "update_user",fill = FieldFill.UPDATE)
    private String updateUser;

    @TableField(value = "update_user_name",fill = FieldFill.UPDATE)
    private String updateUserName;

    @TableField(value = "update_date",fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
