package org.cfm.eshop.inventory.redis.service;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: RedisService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:18
 */
public interface RedisService {

	void set(String key, String value);

	void setObject(String key, Object obj, int expireTime);

	String getObject(String key);

	boolean hasKey(String key);

	void setWithExpireTime(String key, String value, int expireTime);

	String get(String key);

	void delete(String key);
}
