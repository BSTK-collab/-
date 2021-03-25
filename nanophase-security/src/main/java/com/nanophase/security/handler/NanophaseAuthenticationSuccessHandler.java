package com.nanophase.security.handler;

import com.nanophase.common.constant.AuthConstant;
import com.nanophase.common.constant.RedisConstant;
import com.nanophase.common.dto.NanophaseUserLogDTO;
import com.nanophase.common.dto.TokenDTO;
import com.nanophase.common.manager.AsyncManager;
import com.nanophase.common.util.GsonUtil;
import com.nanophase.common.util.JwtUtil;
import com.nanophase.common.util.NetworkUtil;
import com.nanophase.feign.center.CenterApi;
import com.nanophase.security.entity.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author zhj
 * @apiNote 登录成功处理器
 * @since 2021-03-12
 */
@Slf4j
@Component
public class NanophaseAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CenterApi centerApi;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 获取用户信息
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        log.info("登录成功,生成token");
        TokenDTO tokenDTO = new TokenDTO();
        String ipAddress = NetworkUtil.getIpAddress(request);
        String token = JwtUtil.createJwt(null, ipAddress);
        tokenDTO.setToken(token);
        tokenDTO.setPrefix(AuthConstant.TOKEN_PREFIX);
        try {
            redisTemplate.opsForValue().set(RedisConstant.USER_KEY.JWT_TOKEN_PREFIX + user.getUserId(), token,
                    RedisConstant.TOKEN_EXPIRES, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("用户token写入redis失败,toekn:{}", token);
            log.error("用户ID：{}", user.getUserId());
            e.printStackTrace();
        }

        try {
            // 异步调用远程接口 存储用户登录日志
            AsyncManager.getInstance().execute(() -> {
                NanophaseUserLogDTO userLogDTO = new NanophaseUserLogDTO();
                userLogDTO.setNanophaseUserId(user.getUserId());
                userLogDTO.setIpAddr(ipAddress);
                userLogDTO.setLoginStatus(0);
                userLogDTO.setNanophaseUserEmail(user.getUsername());
                centerApi.insertUserLoginInfo(userLogDTO);
            }, null);
        } catch (Exception e) {
            log.error("登录成功处理器，异步保存用户登录信息异常,e:{}", e.getMessage());
            e.printStackTrace();
        }

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(GsonUtil.toJson(tokenDTO));
        } catch (IOException e) {
            log.error("写回token失败");
            e.printStackTrace();
        }
    }
}