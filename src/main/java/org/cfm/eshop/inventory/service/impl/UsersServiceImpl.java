package org.cfm.eshop.inventory.service.impl;

import org.cfm.eshop.inventory.domain.Users;
import org.cfm.eshop.inventory.mapper.UsersMapper;
import org.cfm.eshop.inventory.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: UserServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 14:24
 */
@Service
public class UsersServiceImpl implements UsersService {
	@Resource
	private UsersMapper usersMapper;

	@Override
	public List<Users> getAllUser() {
		return usersMapper.getAll();
	}
}
