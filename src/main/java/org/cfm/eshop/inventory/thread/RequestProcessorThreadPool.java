package org.cfm.eshop.inventory.thread;

import org.cfm.eshop.inventory.request.Request;
import org.cfm.eshop.inventory.request.RequestQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: ThreadPool
 * @Description: 请求处理线程池
 * @Author: fangming_chen
 * @Date: 2021/03/07 15:30
 */
public class RequestProcessorThreadPool {


	/**
	 * 线程池
	 */
	private ExecutorService threadPool = new ThreadPoolExecutor(10,20,6000L,TimeUnit.MILLISECONDS,
			new LinkedBlockingDeque<>());



	public RequestProcessorThreadPool(){
		RequestQueue requestQueue = RequestQueue.getInstance();
		for (int i =0;i<10;i++){
			ArrayBlockingQueue<Request>  arrayBlockingQueue = new ArrayBlockingQueue<Request>(100);
			requestQueue.addQueue(arrayBlockingQueue);
			threadPool.submit(new WorkerThread(arrayBlockingQueue));
		}
	}


	private static class Singleton{
		private static RequestProcessorThreadPool instance;

		static{
			instance = new RequestProcessorThreadPool();
		}

		public static RequestProcessorThreadPool getInstance(){
			return instance;
		}
	}

	/**
	 * 利用JVM的机制保证多线程的并发安全
	 * 内部类的初始化，一定只会发生一次，不管多少个线程去并发初始化
	 * @return
	 */
	public static RequestProcessorThreadPool getInstance(){
		return Singleton.getInstance();
	}

	public static void init(){
		getInstance();
	}
}
