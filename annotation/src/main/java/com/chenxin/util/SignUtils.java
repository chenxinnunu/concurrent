package com.chenxin.util;

import com.chenxin.exception.BaseException;
import com.chenxin.model.DataModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenxin
 * @date 2019/08/20
 */
@Slf4j
public class SignUtils {

	/**
	 *
	 * @param json
	 * @return
	 */
	public static DataModel addSign(String json) {
		if (json == null) {
			log.info("传入json为空,加密结束");
			return null;
		}
		log.info("准备加密的json串:{}", json);
		DataModel dateModel = new DataModel();
		try {
			String data = RSAUtils.encryptData(json, MR_PUBLIC_KEY, RSAUtils.RSA);
			String sign = DigitalSignUtils.addDigitalSign(data, ZN_PRIVATE_KEY, DigitalSignUtils.SHA1WITHRSA);
			dateModel.setData(data);
			dateModel.setSign(sign);
		} catch (Exception e) {
			log.error("浙农加密过程出现错误", e);
		}
		log.info("加密完成,返回相应的实体:{}", dateModel);
		return dateModel;
	}

	/**
	 * @param data 业务数据
	 * @param sign 签名
	 */
	public static String checkSign(String data, String sign) {
		log.info("进入解密验签工具类");
		boolean flag = DigitalSignUtils.verifyDigitalSign(data, sign, MR_PUBLIC_KEY,
				DigitalSignUtils.SHA1WITHRSA);
		String request = null;
		if (flag) {
			request = RSAUtils.decryptData(data, ZN_PRIVATE_KEY, RSAUtils.RSA);
		}else {
			log.info("验签失败");
			throw new BaseException("100003", "验签未通过");
		}
		return request;
	}
}
