package com.nanophase.common.constant;

/**
 * @author zhj
 * @since 2021-03-23
 * @apiNote redis 常量类
 */
public interface RedisConstant {

    /**
     * token 过期时间
     */
    long TOKEN_EXPIRES = 60 * 60 * 2;

    interface USER_KEY{
        String JWT_TOKEN_PREFIX = "Jwt_Token:";
    }
}
