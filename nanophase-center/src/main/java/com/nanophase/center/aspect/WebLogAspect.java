//package com.nanophase.center.aspect;
//
//import com.nanophase.common.annotation.WebLog;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
///**
// * @author zhj
// * @date 2021-03-05
// * 日志切面类
// * https://blog.csdn.net/weixin_29325515/article/details/112324555
// */
//@Aspect
//@Component
//@Order(1)
//public class WebLogAspect {
//    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);
//
//    @Pointcut("@annotation(com.nanophase.common.annotation.WebLog)")
//    public void logPoint() {
//    }
//
//    @Before("logPoint() && @annotation(webLog)")
//    public void before(JoinPoint joinPoint, WebLog webLog) throws Throwable {
//        LOGGER.info("接收到请求============================");
////        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
////        assert attributes != null;
////        HttpServletRequest request = attributes.getRequest();
//        //通过MethodInvocationProceedingJoinPoint内部类连接MethodSignature
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        LOGGER.info("Request Args:" + Arrays.toString(joinPoint.getArgs()));
//        LOGGER.info("方法名称：" + method.getName());
//    }
//}
