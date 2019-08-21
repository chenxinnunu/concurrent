package com.chenxin.test;

import com.chenxin.exception.BaseException;

/**
 * @author chenxin
 * @date 2019/08/21
 */
public class Test {
	public static void main(String[] args) {
		try {
			a();
			System.out.println("fasf");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private static String a() {
		throw new BaseException("faf");
	}
}
