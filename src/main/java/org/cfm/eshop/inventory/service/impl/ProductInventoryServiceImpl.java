package org.cfm.eshop.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cfm.eshop.inventory.domain.ProductInventory;
import org.cfm.eshop.inventory.mapper.ProductInventoryMapper;
import org.cfm.eshop.inventory.redis.service.RedisService;
import org.cfm.eshop.inventory.service.ProductInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.security.Key;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: ProductInfoServiceImpl
 * @Description: 商品库存Service实现类
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:28
 */
@Service
@Slf4j
public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Resource
	private ProductInventoryMapper productInventoryMapper;

	@Resource
	private RedisService redisService;

	/**
	 * 更新商品库存
	 * @param productInventory
	 */
	@Override
	public void updateInventoryCnt(ProductInventory productInventory) {
		productInventoryMapper.updateInventoryCnt(productInventory);
		log.info("[更新数据库库存]==SERVICE数据库更新 -> productInventory = {}", JSONObject.toJSON(productInventory));
	}

	/**
	 * 删除商品库存缓存
	 * @param productInventory
	 */
	@Override
	public void deleteInventoryCache(ProductInventory productInventory) {
		String key = "product_id:"+ productInventory.getProductId();
		log.info("[更新数据库库存]==删除redis缓存 -> key = {}", key);
		redisService.delete(key);
	}

	/**
	 * 根据商品id查询库存
	 * @param productId
	 * @return
	 */
	@Override
	public ProductInventory getByProductId(Long productId) {
		return productInventoryMapper.getByProductId(productId);
	}

	@Override
	public void refreshInventoryCache(ProductInventory productInventoryNew) {
		String key = "product_id:"+ productInventoryNew.getProductId();
		redisService.set(key, String.valueOf(productInventoryNew.getInventoryCnt()));
		log.info("[查询库存]==更新redis缓存success -> key = {}", key);
	}

	@Override
	public ProductInventory getCacheByProductId(Long productId) {
		String key = "product_id:"+ productId;
		String value = redisService.get(key);
		log.info("[查询库存]==获取redis缓存 -> key = {} value = {}", key,value);
		if(!ObjectUtils.isEmpty(value)){
			return new ProductInventory(productId,Long.valueOf(value));
		}
		return null;
	}
}
