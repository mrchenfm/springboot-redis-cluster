package org.cfm.eshop.inventory;

import org.apache.ibatis.session.SqlSessionFactory;
import org.cfm.eshop.inventory.listenter.InitListener;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: AppApplication
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/06 21:15
 */
@SpringBootApplication
@MapperScan("org.cfm.eshop.inventory.mapper")
public class AppApplication {

	public static void main(String[] args ){

		SpringApplication.run(AppApplication.class,args);
	}

	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean(){
		ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
		servletListenerRegistrationBean.setListener(new InitListener());

		return servletListenerRegistrationBean;
	}

}
