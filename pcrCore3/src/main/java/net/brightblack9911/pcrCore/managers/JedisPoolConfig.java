package net.brightblack9911.pcrCore.managers;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class JedisPoolConfig extends GenericObjectPoolConfig
{
	public JedisPoolConfig() {
		setTestWhileIdle(true);
		setMinEvictableIdleTimeMillis(60000L);
		setTimeBetweenEvictionRunsMillis(30000L);
		setNumTestsPerEvictionRun(-1);
	}
}
