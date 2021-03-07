package org.cfm.eshop.inventory.service;

import org.cfm.eshop.inventory.request.Request;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: RequestAsyncProcessService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 16:51
 */
public interface RequestAsyncProcessService {

	void process(Request request);
}
