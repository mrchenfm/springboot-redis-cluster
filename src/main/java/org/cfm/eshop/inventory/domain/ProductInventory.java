package org.cfm.eshop.inventory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: ProductInfo
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {

	/**
	 * 产品id
	 */
	private Long productId;

	/**
	 * 产品库存
	 */
	private Long inventoryCnt;
}
