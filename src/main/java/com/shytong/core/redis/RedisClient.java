package com.shytong.core.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisClient {

	public static void main(String[] args) {
		RedisClient r = RedisClient.redisFactory();
//		String id=IdUtil.getuuId();
//		r.setValue(id, "loginName", "13609520941");
//		r.setValue(id, "accessToken", id);
//		r.setValue(id, "userID", "4af6117f-c413-4959-83ca-e17e0604f2b8");
//		r.setValue(id, "loginType", "app");
//		r.setValue(id, "phone", "13609520941");
		
 

		
		Map<String, String> mm = new HashMap<String, String>();
	
		// RedisClient.redisFactory().expire("aaa", 10);
	}

	private Jedis jedis;// 非切片额客户端连接

	public JedisPool jedisPool;

	private int expireSeconds = 60 * 1;

	public static RedisClient redisFactory() {

		return new RedisClient();
	}

	private RedisClient() {

		this.jedisPool = RedisPool.redisFactory("session").getJedisPool();
		System.out.println(this.jedisPool.getNumActive()
				+ "***********************");
	}

	public Jedis getJedis() {

		return this.jedisPool.getResource();
	}
	public void setValue(String key, String value,int time) {

		Jedis jedis = null;
		try {
	
			jedis = this.jedisPool.getResource();
	
			
			jedis.setex(key, time, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	};
	public void setValue(String token, String key, String value) {

		Jedis jedis = null;
		try {
	
			jedis = this.jedisPool.getResource();
			jedis.hset(token, key, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	};

	public String getValue(String token, String key) {

		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			return jedis.hget(token, key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	};

	public void setZValue(String key, Double score, String member) {

		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			jedis.zadd(key, score, member);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	};

	public boolean isExits(String key) {

		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();

			return jedis.exists(key);

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	public boolean remove(String key) {
		long n = 0;
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			n = jedis.del(key);

		}

		finally {
			if (jedis != null) {
				jedis.close();
			}
		}

		if (n > 0) {

			return true;
		} else {

			return false;
		}

	}

	public void setExpireSeconds(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			jedis.expire(key, seconds);

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	public boolean setMapValues(String key, Map<String, String> map) {
		boolean flag = false;
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();

			if (map != null && jedis != null)

			{
				String res = jedis.hmset(key, map);
				if ("OK".equals(res)) {

					flag = true;
				}

			}

		} finally {
			if (jedis != null) {
				jedis.close();

			}

		}
		return flag;

	};

	public void setValuesByMap(String token, Map<String, String> map) {

		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();

			if (map != null && jedis != null){
				for (String key : map.keySet()) {

					jedis.hset(token, key, map.get(key));

				}

			}



		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	};

	public Map<String, String> getAllValues(String key) {
		if (key == null) {

			return null;
		}

		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();

			if (jedis == null){

				return null;


			}


			return jedis.hgetAll(key);

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	public Map<String, String> getValuesByFiles(String key, String... files) {
		if (key == null)

			return null;

		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();

			if (jedis == null){
				return null;
			}



			List<String> l = jedis.hmget(key, files);

			Map<String, String> m = new HashMap<String, String>();
			for (int n = 0; n < files.length; n++) {

				m.put(files[n], l.get(n));
			}

			return m;

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	public Map<String, String> getValuesByFiles2(String key, String[] files) {
		if (key == null)

			return null;

		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();

			if (jedis == null){

				return null;
			}


			List<String> l = jedis.hmget(key, files);

			Map<String, String> m = new HashMap<String, String>();
			for (int n = 0; n < files.length; n++) {

				m.put(files[n], l.get(n));
			}

			return m;

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	private int getExpireSeconds() {
		return expireSeconds;
	}

	private void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}
