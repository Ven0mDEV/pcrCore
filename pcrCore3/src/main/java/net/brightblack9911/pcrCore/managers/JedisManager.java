package net.brightblack9911.pcrCore.managers;

import redis.clients.jedis.JedisPool;

public class JedisManager
{
	private static JedisManager instance;

	private JedisPool jedisPool;

	public JedisManager() {
		jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379, 0);
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public static JedisManager getInstance() {
		if (instance == null) instance = new JedisManager();
		return instance;
	}
}
