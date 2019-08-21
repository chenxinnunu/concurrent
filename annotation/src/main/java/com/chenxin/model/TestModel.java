package com.chenxin.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chenxin
 * @date 2019/08/21
 */
@Data
public class TestModel {
	@NotBlank(message = "用户名不能为空")
	private String userName;
	@NotBlank(message = "手机号不能为空")
	private String phone;

}
