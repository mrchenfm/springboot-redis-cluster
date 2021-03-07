package org.cfm.eshop.inventory.listenter;

import org.cfm.eshop.inventory.thread.RequestProcessorThreadPool;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: InitListenter
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 15:26
 */
public class InitListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		RequestProcessorThreadPool.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
