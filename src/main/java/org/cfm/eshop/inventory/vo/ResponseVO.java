package org.cfm.eshop.inventory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version v1.0
 * @ProjectName: eshop-inventory
 * @ClassName: ResponseVO
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/07 17:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> implements Serializable {

	private final static  String  SUCCESS = "success";

	private final static  String  FAIL = "fail";

	private String status;

	private String message;

	private T data;

	public static <T>  ResponseVO success(String msg,T data){
		return new ResponseVO(SUCCESS,msg,data);
	}

	public static <T>  ResponseVO fail(String msg,T data){
		return new ResponseVO(SUCCESS,msg,data);
	}

}
