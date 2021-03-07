package org.cfm.eshop.inventory.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cfm.eshop.inventory.domain.ProductInventory;
import org.cfm.eshop.inventory.request.ProductInventoryCacheReloadRequest;
import org.cfm.eshop.inventory.request.ProductInventoryDBDataUpdateRequest;
import org.cfm.eshop.inventory.request.Request;
import org.cfm.eshop.inventory.service.ProductInventoryService;
import org.cfm.eshop.inventory.service.RequestAsyncProcessService;
import org.cfm.eshop.inventory.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: ProductInventoryController
 * @Description: 1、一个更新商品库存的请求过来，然后此时会先删除redis的缓存然后模拟卡顿5秒
 *               2、在卡顿的5秒内我们发送度请求，此时就会读取数据库最新的数据，
 *               3、此时读请求会路由到同一个请求队列，阻塞住不会执行
 *               4、等5秒后会将最新的数据查出来，更新缓存
 * @Author: fangming_chen
 * @Date: 2021/03/07 17:09
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductInventoryController {

	@Resource
	private ProductInventoryService productInventoryService;

	@Resource
	private RequestAsyncProcessService requestAsyncProcessService;

	@RequestMapping("/update/inventoryCnt")
	public ResponseVO updateInventoryCnt(@RequestBody ProductInventory productInventory){
		log.info("[更新数据库库存]==更新数据库库存 -> productInventory={}", JSONObject.toJSON(productInventory));
		try {
			Request request  =
					new ProductInventoryDBDataUpdateRequest(productInventory,productInventoryService);
			requestAsyncProcessService.process(request);
			return ResponseVO.success("修改库存成功",productInventory);
		}catch (Exception e){
			return ResponseVO.fail("修改库存失败",productInventory);
		}
	}

	@RequestMapping("/get/inventoryCnt")
	public ResponseVO getInventoryCnt(Long productId){
		log.info("[更新缓存]==更新缓存 -> productId={}", productId);
		ProductInventory productInventory = new ProductInventory();
		productInventory.setProductId(productId);
		productInventory.setInventoryCnt(-1L);
		try {
			Request request  =
					new ProductInventoryCacheReloadRequest(productInventory,productInventoryService,false);
			requestAsyncProcessService.process(request);
			ProductInventory productInventoryQuery = null;
			Long startTime = System.currentTimeMillis();
			Long endTime = 0L;
			Long costTime = 0L;
			// 将请求扔给service异步去处理以后，就需要while(true)一会儿，在这里hang住
			// 去尝试等待前面有商品库存更新的操作，同时缓存刷新的操作，将最新的数据刷新到缓存中
			while(true){
				log.info("startTime = {}",startTime);
				if(costTime > 1000){
					break;
				}
				productInventoryQuery = productInventoryService.getCacheByProductId(productId);
				if(productInventoryQuery != null){
					log.info("[更新缓存]=={}毫秒读取缓存成功 -> productInventoryQuery={}",costTime, productInventoryQuery);
					return ResponseVO.success("查询库存成功",productInventoryQuery);
				}else{
					Thread.sleep(200);
					endTime = System.currentTimeMillis();
					costTime = endTime - startTime;
				}
			}
			productInventoryQuery = productInventoryService.getByProductId(productId);
			if(productInventoryQuery != null){
				log.info("[更新缓存]==强制更新缓存");
				request  =
						new ProductInventoryCacheReloadRequest(productInventory,productInventoryService,true);

				requestAsyncProcessService.process(request);
				return ResponseVO.success("查询库存成功",productInventoryQuery);
			}
			return ResponseVO.success("查询库存成功",productInventory);
		}catch (Exception e){
			return ResponseVO.fail("查询库存失败",productInventory);
		}
	}
}
