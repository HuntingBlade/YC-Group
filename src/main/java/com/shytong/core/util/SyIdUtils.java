package com.shytong.core.util;

import java.util.UUID;

/**
 * @author sytong
 * @Package com.shytong.core.util
 * @Description:
 * @date 2018-01-1717:23
 */
public class SyIdUtils {
    public static SnowflakeIdWorker snowflakeIdWorker;
    static {
        snowflakeIdWorker=new SnowflakeIdWorker(1,1);
    }
    public static long nextId(){
        return  snowflakeIdWorker.nextId();
    }
    public static String uuid(){

        return UUID.randomUUID().toString().replace("-","");
    }
}
