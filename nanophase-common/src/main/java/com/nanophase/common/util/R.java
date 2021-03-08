package com.nanophase.common.util;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {

    public R() {
        this.put("code", "200");
        this.put("msg", "success");
    }

    public static R success(){
        return new R();
    }

    public static R success(String msg){
        R r = new R();
        r.put("msg",msg);
        return r;
    }

    public static R success(Map<String,Object> msg){
        R r = new R();
        r.putAll(msg);
        return r;
    }

    public R put(String key,Object value){
        super.put(key,value);
        return this;
    }

    public static R error(){
        return error("500","未知异常");
    }

    public static R error(String msg){
        return error("500",msg);
    }

    public static R error(String code,Object value){
        R r = new R();
        r.put("code",code);
        r.put("msg",value);
        return r;
    }
}
