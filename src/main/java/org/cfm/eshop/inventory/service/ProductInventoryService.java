package org.cfm.eshop.inventory.service;

import org.cfm.eshop.inventory.domain.ProductInventory;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: ProductInfoService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:27
 */
public interface ProductInventoryService {

	void updateInventoryCnt(ProductInventory productInventory);

	void deleteInventoryCache(ProductInventory productInventory);

	ProductInventory getByProductId(Long productId);

	void refreshInventoryCache(ProductInventory productInventoryNew);

	ProductInventory getCacheByProductId(Long productId);
}
