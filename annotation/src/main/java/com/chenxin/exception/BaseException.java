package com.chenxin.exception;

/**
 * @author chenxin
 * @date 2019/08/21
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = -7654774193130809084L;
	private String code;
	private String message;

	public BaseException() {
	}

	public BaseException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}


	public BaseException(Throwable ex) {
		super(ex);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable ex) {
		super(message, ex);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
