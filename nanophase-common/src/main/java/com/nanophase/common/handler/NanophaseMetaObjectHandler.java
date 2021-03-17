package com.nanophase.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zhj
 * @since 2021-03-17
 * @apiNote mybatis-plus自动填充
 */
@Component
public class NanophaseMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createUser","",metaObject);
        this.setFieldValByName("createUserName","",metaObject);
        this.setFieldValByName("createDate", LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateUser","",metaObject);
        this.setFieldValByName("updateUserName","",metaObject);
        this.setFieldValByName("updateDate",LocalDateTime.now(),metaObject);
    }
}
