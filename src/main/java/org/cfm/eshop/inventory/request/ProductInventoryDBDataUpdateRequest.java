package org.cfm.eshop.inventory.request;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cfm.eshop.inventory.domain.ProductInventory;
import org.cfm.eshop.inventory.service.ProductInventoryService;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: DataUpdateRequest
 * @Description: 此时会发送请求过来，要求更新库存，这就是数据跟新请求
 *              cache aside pattern
 *              （1）删除缓存
 *              （2）更新库存
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:04
 */
@Slf4j
public class ProductInventoryDBDataUpdateRequest implements Request {

	/**
	 * 商品数量
	 */
	private ProductInventory productInventory;

	private ProductInventoryService productInventoryService;


	public ProductInventoryDBDataUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService){
		this.productInventory = productInventory;
		this.productInventoryService = productInventoryService;
	}

	@Override
	public void process() {
		log.info("[更新数据库库存]==更新数据库库存 -> productInventory = {}", JSONObject.toJSON(productInventory));
		//删除redis缓存
		productInventoryService.deleteInventoryCache(productInventory);
		log.info("[更新数据库库存]==删除redis缓存 -> productInventory = {}", JSONObject.toJSON(productInventory));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//更新数据库库存
		productInventoryService.updateInventoryCnt(productInventory);
	}

	@Override
	public Long getProductId() {
		return productInventory.getProductId();
	}

	@Override
	public Boolean forceRefresh() {
		return false;
	}

}
