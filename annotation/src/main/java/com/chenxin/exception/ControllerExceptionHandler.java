package com.chenxin.exception;

import com.chenxin.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author chenxin
 * @date 2019/08/21
 */
@ControllerAdvice(basePackages = "com.chenxin.controller")
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {

	 // 基于@ExceptionHandler异常处理,拦截所有异常
	@ExceptionHandler(value = Exception.class)
	public DataModel exceptionHandle(Throwable throwable) {
		log.error("", throwable);
		if (throwable instanceof BaseException) {
			BaseException ex = (BaseException) throwable;
			return new DataModel(ex.getCode(), ex.getMessage());
		} else if (throwable instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException mane = (MethodArgumentNotValidException) throwable;
			BindingResult bindingResult = mane.getBindingResult();
			return new DataModel("100002", bindingResult.getAllErrors().get(0).getDefaultMessage());
		} else if (throwable instanceof BindException) {
			BindException bindException = (BindException) throwable;
			BindingResult bindingResult = bindException.getBindingResult();
			return new DataModel("100002", bindingResult.getAllErrors().get(0).getDefaultMessage());
		} else {
			return new DataModel("100000", throwable.getMessage());
		}
	}
}

