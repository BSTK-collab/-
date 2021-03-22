package com.nanophase.common.dto;

import lombok.Data;

/**
 * @author zhj
 * @since 2021-03-22
 * @apiNote 返回的token实体类
 */
@Data
public class TokenDTO {
    private String token;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * token过期时间
     */
    private String expires;

    /**
     * token前缀
     */
    private String prefix;
}
