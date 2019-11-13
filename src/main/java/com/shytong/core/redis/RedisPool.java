package com.shytong.core.redis;


import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.Map;

public class RedisPool {
	private JedisPool jedisPool;// 非切片连接池
	private ShardedJedis shardedJedis;// 切片额客户端连接
	private ShardedJedisPool shardedJedisPool;// 切片连接池
	private int maxIdle;
	private String host;
	private int port;
	private int MaxTotal;

	private int timeout = 5000;

	private String password;

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxTotal() {
		return MaxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		MaxTotal = maxTotal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static Map<String, RedisPool> getRedisPools() {
		return redisPools;
	}

	public static void setRedisPools(Map<String, RedisPool> redisPools) {
		RedisPool.redisPools = redisPools;
	}

	private int expireSeconds = 60 * 1;
	private long maxWaitMillis;
	private boolean TestOnBorrow = false;
	private static Map<String, RedisPool> redisPools = new HashMap<String, RedisPool>();

	// private static RedisPool redisPool=null;
	private static class RedisLazyHolder {

		// ProConfig.getProValue("redisHost")
		// ;Integer.parseInt(ProConfig.getProValue("redisPort"));
		// redisHost=127.0.0.1
		// redisPort=6379

		// private static final RedisPool INSTANCE = new RedisPool();

	}

	public static RedisPool redisFactory(String flag) {

		if (redisPools.get(flag) == null) {

			synchronized (RedisPool.class) {
				if (redisPools.get(flag) == null) {

					redisPools.put(flag, new RedisPool(flag));

				}

			}

		}

		return redisPools.get(flag);

		// ProConfig.getProValue(key)

		// return RedisLazyHolder.INSTANCE;
	}

	private RedisPool(String flag) {
		initialPool(flag);

	}

	public void returnResource(Jedis jedis) {

		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool(String flag) {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
//
//		this.maxIdle = Integer.parseInt(ProConfig.getProValues(flag
//				+ "_maxIdle"));
//		this.host = ProConfig.getProValues(flag + "_redisHost");
//		this.port = Integer.parseInt(ProConfig
//				.getProValues(flag + "_redisPort"));
//
//		this.MaxTotal = Integer.parseInt(ProConfig.getProValues(flag
//				+ "_MaxTotal"));
//		this.maxWaitMillis = Long.parseLong(ProConfig.getProValues(flag
//				+ "_maxWaitMillis"));
//
//
//		this.password = ProConfig.getProValues(flag + "_password");

		config.setMaxIdle(this.maxIdle);
		config.setMaxWaitMillis(this.maxWaitMillis);
		config.setTestOnBorrow(this.TestOnBorrow);
		config.setMaxTotal(this.MaxTotal);

		jedisPool = new JedisPool(config, this.host, this.port, this.timeout,
				this.password);

	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	private void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	private ShardedJedis getShardedJedis() {
		return shardedJedis;
	}

	private void setShardedJedis(ShardedJedis shardedJedis) {
		this.shardedJedis = shardedJedis;
	}

	private ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	private void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	private String getHost() {
		return host;
	}

	private void setHost(String host) {
		this.host = host;
	}

	private int getPort() {
		return port;
	}

	private void setPort(int port) {
		this.port = port;
	}

	private long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	private void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	private boolean isTestOnBorrow() {
		return TestOnBorrow;
	}

	private void setTestOnBorrow(boolean testOnBorrow) {
		TestOnBorrow = testOnBorrow;
	}

	private int getExpireSeconds() {
		return expireSeconds;
	}

	private void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}
