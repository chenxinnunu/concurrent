package com.chenxin.common.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数解密加密注解
 * @author chenxin
 * @date 2019/08/20
 */
//注解的范围，这里是可以用于方法和类
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface SecurityParameter {

	/**
	 * 入参是否解密，默认true解密
	 */
	boolean inDecode() default true;

	/**
	 * 出参是否加密，默认true加密
	 */
	boolean outEncode() default true;
}
