package com.chenxin.common.advice;

import com.alibaba.fastjson.JSON;
import com.chenxin.common.annotation.SecurityParameter;
import com.chenxin.model.DataModel;
import com.chenxin.util.DESHelper;
import com.chenxin.util.SignUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回数据加密
 * @author chenxin
 * @date 2019/08/20
 */
@ControllerAdvice(basePackages = "com.chenxin.controller")
@Slf4j
public class MyResponseBodyAdvice implements ResponseBodyAdvice {


	@Override
	public boolean supports(MethodParameter methodParameter, Class aClass) {
		return true;
	}

	@Override
	public DataModel beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		boolean encode = false;
		if (methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class)) {
			//获取注解配置的包含和去除字段
			SecurityParameter serializedField = methodParameter.getMethodAnnotation(SecurityParameter.class);
			//出参是否需要加密
			encode = serializedField.outEncode();
		}
		DataModel dataModel = (DataModel) body;
		dataModel.setCode("200");
		dataModel.setMsg("成功");
		if (encode) {
			log.info("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行加密");
				dataModel = SignUtils.addSign(dataModel.getData());
			}
		return dataModel;
	}
}
