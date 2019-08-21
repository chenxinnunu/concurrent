package com.chenxin.controller;

import com.alibaba.fastjson.JSON;
import com.chenxin.common.annotation.SecurityParameter;
import com.chenxin.model.DataModel;
import com.chenxin.model.TestModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author chenxin
 * @date 2019/08/20
 */
@RestController
@RequestMapping("test")
public class TestController {
	@PostMapping("annotation")
	@SecurityParameter
	public DataModel getCode(@RequestBody @Valid TestModel testModel) {
		System.out.println(testModel);
		DataModel dataModel = new DataModel();
		dataModel.setData(JSON.toJSONString(testModel));
		return dataModel;
	}
}
