package org.cfm.eshop.inventory.controller;

import org.cfm.eshop.inventory.domain.Users;
import org.cfm.eshop.inventory.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: UsersController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 14:28
 */
@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@RequestMapping("/showAll")
	public List<Users> showAllUsers(){
		return usersService.getAllUser();
	}
}
