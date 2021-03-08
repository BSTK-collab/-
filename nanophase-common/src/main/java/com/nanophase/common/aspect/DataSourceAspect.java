package com.nanophase.common.aspect;

import com.nanophase.common.annotation.ReadDB;
import com.nanophase.common.annotation.WriteDB;
import com.nanophase.common.config.DynamicDataSourceHolder;
import com.nanophase.common.constant.CenterConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhj
 * @date 2021-03-05
 * 自定义注解 数据源读写分离切面类
 */
@Component
@Aspect
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(com.nanophase.common.annotation.ReadDB)")
    public void readDB() {
    }

    @Pointcut("@annotation(com.nanophase.common.annotation.WriteDB)")
    public void writeDB() {
    }

    @Before(value = "readDB() || writeDB()")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("绑定数据源");
        Class<?> clazz = joinPoint.getTarget().getClass();
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        Class<?>[] parameterTypes = ((MethodSignature) signature).getMethod().getParameterTypes();
        try {
            // 必须使用methodName+parameterTypes获取method对象 否则会抛出异常
            Method method = clazz.getMethod(methodName,parameterTypes);
            // 读库
            if (method.isAnnotationPresent(ReadDB.class)) {
                DynamicDataSourceHolder.setRoutingKey(CenterConstant.db.SLAVE);
            }
            // 写库
            if (method.isAnnotationPresent(WriteDB.class)) {
                DynamicDataSourceHolder.setRoutingKey(CenterConstant.db.MASTER);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            LOGGER.error("Class Name get error,e:{}", e.getMessage());
        }
    }

    @After(value = "readDB() || writeDB()")
    public void after(JoinPoint joinPoint) {
        LOGGER.info("ThreadLocal ----- 清除该线程绑定的数据源");
        DynamicDataSourceHolder.deleteRoutingKey();
    }
}
