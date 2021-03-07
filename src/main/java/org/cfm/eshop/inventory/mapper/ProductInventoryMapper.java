package org.cfm.eshop.inventory.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cfm.eshop.inventory.domain.ProductInventory;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: InventoryCntDao
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:14
 */
@Mapper
public interface ProductInventoryMapper {

	void updateInventoryCnt(ProductInventory productInventory);

	ProductInventory getByProductId(Long productId);
}
