package com.shytong.core.sy.session;

import org.springframework.data.redis.core.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author sytong
 * @Package com.shytong.core.sy.session
 * @Description:
 * @date 2018-04-1613:30
 */
public class SySessionUtil {


    public static RedisTemplate redisTemplate;

    public static HashOperations<String, String, Object> hashOperations;
    public static ValueOperations<String, Object> valueOperations;
    public static SetOperations<String, Object> setOperations;
    public static ZSetOperations<String, Object> zSetOperations;
    public static int expire=1800;

public static void  setRedisTemplate(RedisTemplate redisTemplat ){
    redisTemplate=redisTemplat;
    hashOperations=redisTemplate.opsForHash();
    valueOperations=redisTemplate.opsForValue();
    setOperations= redisTemplate.opsForSet();
    zSetOperations=redisTemplate.opsForZSet();
}
  public  static SySession getSession(String key, boolean flag){
       Map m= redisTemplate.opsForHash().entries(key);
        if(m==null){
            if(flag){
                return new SySession(key,null);
            }
            return null;
        }
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);

        return  new SySession(key,m);

}
    public  static SySession getSession(String key){

       return getSession(key,true);

    }

    public static void  del(String h, String... key){
//        redisTemplate.opsForHash().
        redisTemplate.opsForHash().delete(h,key);



    }
    public static void  hmSet(String key,Map value){
//        redisTemplate.opsForHash().
        redisTemplate.opsForHash().putAll(key,value);



    }
}
