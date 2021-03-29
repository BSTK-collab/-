package com.nanophase.common.aspect;

import com.nanophase.common.annotation.WebLog;
import com.nanophase.common.dto.NanophaseSystemLogDTO;
import com.nanophase.common.manager.AsyncManager;
import com.nanophase.feign.center.CenterApi;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhj
 * @date 2021-03-05
 * 日志切面类
 * https://blog.csdn.net/weixin_29325515/article/details/112324555
 */
@Aspect
@Component
//@Order(1)
public class WebLogAspect {

    @Autowired
    private CenterApi centerApi;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("@annotation(com.nanophase.common.annotation.WebLog)")
    public void logPoint(){}

    @Before("logPoint() && @annotation(webLog)")
    public void before(JoinPoint joinPoint, WebLog webLog) {
        LOGGER.info("接收到请求============================");
//        Object proceed = joinPoint.proceed();
//        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        assert attributes != null;
//        HttpServletRequest request = attributes.getRequest();
        //通过MethodInvocationProceedingJoinPoint内部类连接MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        WebLog annotation = method.getAnnotation(WebLog.class);
        String requestParam = Arrays.toString(joinPoint.getArgs());
        String methodName = method.getName();
        String taskName = annotation.value();
        LOGGER.info("Request Args:" + requestParam);
        LOGGER.info("Method Name:" + methodName);
        LOGGER.info("执行业务名称:" + taskName);
        try{
            // 异步保存系统日志
            AsyncManager.getInstance().execute(() -> {
                NanophaseSystemLogDTO nanophaseSystemLogDTO = new NanophaseSystemLogDTO();
                nanophaseSystemLogDTO.setRequestMethod(methodName);
                // 请求参数
                nanophaseSystemLogDTO.setRequestParam(requestParam);
                // 任务名称
                nanophaseSystemLogDTO.setSystemLogName(taskName);
                centerApi.insertSystemLog(nanophaseSystemLogDTO);
            },null);
        }catch (Exception e){
            e.printStackTrace();
        }
//        return proceed;
    }
}
