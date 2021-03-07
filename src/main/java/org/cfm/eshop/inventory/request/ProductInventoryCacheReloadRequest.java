package org.cfm.eshop.inventory.request;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cfm.eshop.inventory.domain.ProductInventory;
import org.cfm.eshop.inventory.service.ProductInventoryService;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: ProductInventoryCacheReloadRequest
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:36
 */
@Slf4j
public class ProductInventoryCacheReloadRequest implements Request {
	/**
	 * 商品数量
	 */
	private ProductInventory productInventory;

	private ProductInventoryService productInventoryService;

	private Boolean forceRefresh;

	public ProductInventoryCacheReloadRequest(ProductInventory productInventory, ProductInventoryService productInventoryService,Boolean forceRefresh){
		this.productInventory = productInventory;
		this.productInventoryService = productInventoryService;
		this.forceRefresh = forceRefresh;
	}

	@Override
	public void process() {
		ProductInventory productInventoryNew = productInventoryService.getByProductId(productInventory.getProductId());
		log.info("[更新缓存]==查询商品库存 -> productInventoryNew = {}", JSONObject.toJSON(productInventoryNew));
		productInventoryService.refreshInventoryCache(productInventoryNew);
	}

	@Override
	public Long getProductId(){
		return productInventory.getProductId();
	}

	@Override
	public Boolean forceRefresh() {
		return forceRefresh;
	}
}
