package org.cfm.eshop.inventory.redis.service.impl;

import com.alibaba.fastjson.JSON;
import org.cfm.eshop.inventory.redis.service.RedisService;
import org.cfm.eshop.inventory.utils.JedisClusterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: RedisServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:19
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {
	@Resource
	private JedisCluster jedisCluster;


	private static final Logger LOGGER = LoggerFactory.getLogger(JedisClusterUtil.class);

	/**
	 *
	 * @param key   缓存key
	 * @param value 缓存value
	 */
	@Override
	public void set(String key, String value) {
		jedisCluster.set(key, value);
		LOGGER.debug("JedisClusterUtil:set cache key={},value={}", key, value);
	}

	/**
	 * 设置缓存对象
	 *
	 * @param key 缓存key
	 * @param obj 缓存value
	 */
	@Override
	public  void setObject(String key, Object obj, int expireTime) {
		jedisCluster.setex(key, expireTime, JSON.toJSONString(obj));
	}

	/**
	 * 获取指定key的缓存
	 *
	 * @param key---JSON.parseObject(value, User.class);
	 */
	@Override
	public String getObject(String key) {
		return jedisCluster.get(key);
	}

	/**
	 * 判断当前key值 是否存在
	 *
	 * @param key
	 */
	@Override
	public boolean hasKey(String key) {
		return jedisCluster.exists(key);
	}


	/**
	 * 设置缓存，并且自己指定过期时间
	 *
	 * @param key
	 * @param value
	 * @param expireTime 过期时间
	 */
	@Override
	public void setWithExpireTime(String key, String value, int expireTime) {
		jedisCluster.setex(key, expireTime, value);
		LOGGER.debug("JedisClusterUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value, expireTime);
	}


	/**
	 * 获取指定key的缓存
	 *
	 * @param key
	 */
	@Override
	public String get(String key) {
		String value = jedisCluster.get(key);
		LOGGER.debug("JedisClusterUtil:get cache key={},value={}", key, value);
		return value;
	}

	/**
	 * 删除指定key的缓存
	 *
	 * @param key
	 */
	@Override
	public void delete(String key) {
		jedisCluster.del(key);
		LOGGER.debug("JedisClusterUtil:delete cache key={}", key);
	}

}
