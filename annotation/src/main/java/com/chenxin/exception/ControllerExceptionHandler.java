package com.chenxin.exception;

import com.chenxin.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author chenxin
 * @date 2019/08/21
 */
@ControllerAdvice(basePackages = "com.chenxin.controller")
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {
/*
	 * 基于@ExceptionHandler异常处理,拦截所有异常
	 */
	@ExceptionHandler(value = Exception.class)
	public DataModel exceptionHandle(Throwable throwable) {
		log.error("", throwable);
		DataModel dataModel = new DataModel();
		dataModel.setData("");
		dataModel.setSign("");
		dataModel.setFile("");
		if (throwable instanceof BaseException) {
			BaseException ex = (BaseException) throwable;
			dataModel.setCode(ex.getCode());
			dataModel.setMsg(ex.getMessage());
			return dataModel;
		} else if (throwable instanceof ConstraintViolationException) {
			ConstraintViolationException ex = (ConstraintViolationException) throwable;
			dataModel.setCode("-1");
			dataModel.setMsg(ex.getConstraintViolations().iterator().next().getMessage());
			return dataModel;
		} else if (throwable instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException mane = (MethodArgumentNotValidException) throwable;
			BindingResult bindingResult = mane.getBindingResult();
			dataModel.setCode("-1");
			dataModel.setMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
			return dataModel;
		} else if (throwable instanceof BindException) {
			BindException bindException = (BindException) throwable;
			BindingResult bindingResult = bindException.getBindingResult();
			dataModel.setCode("-1");
			dataModel.setMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
			return dataModel;
		} else {
			dataModel.setCode("-1");
			dataModel.setMsg(throwable.getMessage());
			return dataModel;
		}
	}
}

