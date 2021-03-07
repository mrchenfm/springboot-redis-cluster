package org.cfm.eshop.inventory.service;

import org.cfm.eshop.inventory.domain.Users;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: UsersService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 14:24
 */
public interface UsersService {

	List<Users> getAllUser();
}
