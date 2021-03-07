package org.cfm.eshop.inventory.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cfm.eshop.inventory.request.Request;
import org.cfm.eshop.inventory.request.RequestQueue;
import org.cfm.eshop.inventory.service.RequestAsyncProcessService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: RequestAsyncProcessServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:53
 */
@Service
@Slf4j
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {

	/**
	 *
	 */
	@Override
	public void process(Request request) {
		ArrayBlockingQueue<Request> arrayBlockingQueue = getRoutingQueue(request);
		try {
			arrayBlockingQueue.put(request);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取路由到内存队列
	 * @param request
	 * @return
	 */
	private ArrayBlockingQueue<Request> getRoutingQueue(Request request){

		RequestQueue requestQueue = RequestQueue.getInstance();

		String key = String.valueOf(request.getProductId());
		int h;
		int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

		//对hash值取模，将hash值路由到指定的内存队列中
		int index = (requestQueue.queueSize() - 1) & hash;

		log.info("productId -> {},index -> {}",request.getProductId(),index);
		return requestQueue.getQueue(index);
	}
}
