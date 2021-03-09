package com.nanophase.common.DTO;

import lombok.Data;

/**
 * @author zhj
 * @date 2021-03-09
 */
@Data
public class NanophaseUserDTO {

    /**
     * 登录账号
     */
    private String email;

    /**
     * 登录密码
     */
    private String password;
}
