package com.nanophase.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * @author zhj
 * @since 2021-03-25
 * @apiNote gson序列化工具类
 */
public class GsonUtil {

    public static String toJson(Object o){
        return new Gson().toJson(o);
    }
}
