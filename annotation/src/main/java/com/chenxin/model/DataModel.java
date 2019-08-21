package com.chenxin.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenxin
 * @date 2019/08/20
 */
@Data
public class DataModel implements Serializable {
	//响应代码       0为成功，其他为失败。公共参数
	private String code;
	//描述信息        公共参数
	private String msg;
	//业务数据	 	加密密文
	public String data;
	//签名	 	签名数据
	public String sign;
	//文件内容	 	文件下载时使用
	private String file;

	public DataModel() {
		this.code = "000000";
		this.msg = "请求成功";
	}

	public DataModel(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
