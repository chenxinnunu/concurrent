package com.chenxin.rabbitmq;

/**
 * @author wuhuan 2018-10-16
 */
/**
 * @author v_wuhuanhuan_dxm
 *
 */
public interface StringConstants {

	/** 空字符串 */
	String EMPTY = "";
	/** 一个空格 */
	String BLANK_ONE = " ";
	/** 逗号 */
	String COMMA = ",";
	/** 点 */
	String DOT = ".";
	/** 竖线 */
	String VERTICAL = "|";
	/** 冒号 */
	String COLON = ":";
	
	/** 下划线 */
	String UNDERLINE = "_";
	/** 成功英文(小写) */
	String SUCCESS_LOWER = "success";
	/** 成功英文(大写) */
	String SUCCESS_UPPER = "SUCCESS";
	
	
	/** @content {@value} */
	String ZERO = "0";
	
	/** 
	 * @description 100 <br>
	 *   金额单位是分 需要除以100才可入库
	 */
	String HUNDRED = "100";
	/** 
	 * @description 10000 <br>
	 *   利率单位有万分之
	 */
	String TEN_THOUSAND = "10000";
	
	/**
	 * 用于redis取数据的通用key开头之一
	 */
	String KEYSET = "KEYSET";
	/**
	 * 平台英文,用于redis等
	 */
	String PLATFORM = "PLATFORM";
	
}
