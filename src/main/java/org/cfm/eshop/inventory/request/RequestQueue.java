package org.cfm.eshop.inventory.request;

import lombok.Data;
import org.cfm.eshop.inventory.thread.RequestProcessorThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: RequestQueue
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 15:47
 */
public class RequestQueue {

	/**
	 * 内存队列
	 */
	private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();


	public Map<Long, Boolean> getQueueFlag() {
		return queueFlag;
	}

	private Map<Long,Boolean> queueFlag = new ConcurrentHashMap<>();

	public List<ArrayBlockingQueue<Request>> getQueues() {
		return queues;
	}

	/**
	 * 获取内存队列size
	 * @return
	 */
	public int queueSize() {
		return queues.size();
	}

	public ArrayBlockingQueue<Request> getQueue(int index) {
		return queues.get(index);
	}


	private static class Singleton{
		private static RequestQueue instance;

		static{
			instance = new RequestQueue();
		}

		public static RequestQueue getInstance(){
			return instance;
		}
	}

	/**
	 * 利用JVM的机制保证多线程的并发安全
	 * 内部类的初始化，一定只会发生一次，不管多少个线程去并发初始化
	 * @return
	 */
	public static RequestQueue getInstance(){
		return RequestQueue.Singleton.getInstance();
	}

	/**
	 *
	 * @param queue
	 */
	public void addQueue(ArrayBlockingQueue<Request> queue){
		queues.add(queue);
	}
}
