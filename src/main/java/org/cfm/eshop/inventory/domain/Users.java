package org.cfm.eshop.inventory.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: Users
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 14:20
 */
@Data
public class Users implements Serializable {

	private Long id;

	private String userName;

	private Integer userAge;
}
