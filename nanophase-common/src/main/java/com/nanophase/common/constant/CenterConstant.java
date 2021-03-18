package com.nanophase.common.constant;

/**
 * @author zhj
 * @date 2021-03-08
 * center服务常量类
 */
public interface CenterConstant {

    interface db {
        String MASTER = "master";
        String SLAVE = "slave";
    }

    interface status {
        Integer STATUS_ZERO = 0;
        Integer STATUS_ONE = 1;
    }

    interface User {
        /**
         * 用户是否被禁用 否
         */
        Integer USER_STATUS_0 = 0;

        /**
         * 用户是否被禁用 是
         */
        Integer USER_STATUS_1 = 1;

        /**
         * 手机号长度
         */
        Integer PHONE_SIZE = 11;
    }
}
