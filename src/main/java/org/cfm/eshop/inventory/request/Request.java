package org.cfm.eshop.inventory.request;


/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: Request
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 15:41
 */
public interface Request {

	void process();

	Long getProductId();

	Boolean forceRefresh();

}
