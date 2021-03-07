package org.cfm.eshop.inventory.thread;

import lombok.extern.slf4j.Slf4j;
import org.cfm.eshop.inventory.request.ProductInventoryCacheReloadRequest;
import org.cfm.eshop.inventory.request.ProductInventoryDBDataUpdateRequest;
import org.cfm.eshop.inventory.request.Request;
import org.cfm.eshop.inventory.request.RequestQueue;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: WorkerThread
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 15:42
 */
@Slf4j
public class WorkerThread implements Callable<Boolean> {

	/**
	 * 自己监控的队列
	 */
	private ArrayBlockingQueue<Request> queues;

	public WorkerThread(ArrayBlockingQueue<Request> queues){
		this.queues = queues;
	}
	@Override
	public Boolean call() throws Exception {
		try {
			while (true){

				Request request = queues.take();
				log.info("request = {}",request.getClass());
				if(!request.forceRefresh()){
					//先做去重
					RequestQueue requestQueue = RequestQueue.getInstance();
					Map<Long, Boolean> queueFlag = requestQueue.getQueueFlag();
					if(request instanceof ProductInventoryDBDataUpdateRequest){
						queueFlag.put(request.getProductId(),true);
					}
					if(request instanceof ProductInventoryCacheReloadRequest){
						Boolean booleanValue = queueFlag.get(request.getProductId());

						if(booleanValue == null){
							queueFlag.put(request.getProductId(),false);
						}

						//如果是刷新缓存请求，如果flag不为空并且值为true，说明之前有一个商品库存更新的请求
						if(booleanValue != null && booleanValue){
							queueFlag.put(request.getProductId(),false);
						}
						//如果是刷新缓存请求，如果flag不为空并且值为false，说明之前有一个商品库存更新的请求和刷新缓存的请求
						if(booleanValue != null && !booleanValue){
							return true;
						}
					}
				}
				request.process();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
}
