package com.nanophase.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zhj
 * @since 2021-03-19
 * @apiNote Jwt工具类
 */
public class JwtUtil {

    public static String createJwt(){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,2);
        return JWT.create().withSubject("subject")
                .withIssuedAt(date)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256("1"));
    }

    public static String parseToken (String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256("1")).build().verify(token);
        System.out.println(verify);
        return verify.getSubject();
    }

    public static void main(String[] args) {
        System.out.println(createJwt());
    }
}
