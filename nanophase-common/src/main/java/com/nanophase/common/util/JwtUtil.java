package com.nanophase.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.nanophase.common.dto.NanophaseUserDTO;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhj
 * @since 2021-03-19
 * @apiNote Jwt工具类
 */
public class JwtUtil {

    private static final String secret = "nanophase-secret";

    public static String createJwt(NanophaseUserDTO nanophaseUserDTO,String ipAddr){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY,2);
        return JWT.create().withSubject("subject")
                .withIssuedAt(date)
                .withExpiresAt(calendar.getTime())
                // 存入用户登录时的ip地址
                .withClaim("ip",ipAddr)
                .sign(Algorithm.HMAC256(secret));
    }

    public static String parseToken (String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        System.out.println(verify);
        Claim userInfo = verify.getClaim("userinfo");
        String s = userInfo.asString();
        System.out.println("0----->" + s);
        return verify.getSubject();
    }
}
