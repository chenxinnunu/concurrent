package com.chenxin.common.advice;

import com.alibaba.fastjson.JSONObject;
import com.chenxin.common.annotation.SecurityParameter;
import com.chenxin.model.DataModel;
import com.chenxin.util.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 请求数据解密
 *
 * @author chenxin
 * @date 2019/08/20
 */
@ControllerAdvice(basePackages = "com.chenxin.controller")
@Slf4j
public class MyRequestBodyAdvice implements RequestBodyAdvice {

	@Override
	public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
		return true;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
		return body;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
		try {
			boolean encode = false;
			if (methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class)) {
				//获取注解配置的包含和去除字段
				SecurityParameter serializedField = methodParameter.getMethodAnnotation(SecurityParameter.class);
				//入参是否需要解密
				encode = serializedField.inDecode();
			}
			if (encode) {
				log.info("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密");
				return new MyHttpInputMessage(inputMessage);
			} else {
				return inputMessage;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密出现异常：" + e.getMessage());
			return inputMessage;
		}
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
		return body;
	}

	static class MyHttpInputMessage implements HttpInputMessage {
		private HttpHeaders headers;

		private InputStream body;

		MyHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
			this.headers = inputMessage.getHeaders();
			this.body = IOUtils.toInputStream(easpString(IOUtils.toString(inputMessage.getBody(), "UTF-8")));
		}

		@Override
		public InputStream getBody() {
			return body;
		}

		@Override
		public HttpHeaders getHeaders() {
			return headers;
		}

		String easpString(String request) {
			log.info("接收的加密数据：" + request);
			if (StringUtils.isNotBlank(request)) {
				DataModel dataModel = JSONObject.parseObject(request, DataModel.class);
				return SignUtils.checkSign(dataModel.getData(), dataModel.getSign());
			}
			return "";
		}
	}
}
