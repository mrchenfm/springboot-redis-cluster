package org.cfm.eshop.inventory.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: RedisConfig
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 14:55
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.config")
public class RedisConfig{

	/**
	 * 集群节点
	 */
	private List<String> clusterNodes;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 连接超时时间
	 */
	private int connectionTimeout;

	/**
	 * 读取数据超时时间
	 */
	private int soTimeout;

	/**
	 * 最大尝试次数
	 */
	private int maxAttempts;

}