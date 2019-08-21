package com.chenxin.util;

import com.alibaba.fastjson.JSON;
import com.chenxin.exception.BaseException;
import com.chenxin.model.DataModel;
import com.chenxin.model.TestModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenxin
 * @date 2019/08/20
 */
@Slf4j
public class SignUtils {

	// 浙农小贷公钥
	// private static final String MR_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMtnm7JL21eoIbzpHJD8EPWZP1OZTLrWFMGgUsdfIuuN64SqIqxmctHp+x2r2QQCrTbFap43I0NqYVakY9nVtVuTygB9rYYfusgVtJwYCXZl53p78zd1kfMp4ZEosg0oi4qwbKhOEmSThWL14ZSaMH+majKY/rmKvvLaiC9HgdPQIDAQAB";
	// 明日公钥
	private static final String MR_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCM6oQ2DZdekQCu5ArWF39Hwc8UOp6w9VTOr5AMRnFcagfozmKqMnATKLgUqYL+wMue+r6njiO9b8c42JBAGv3NGls7/Rzu/yrHwMijTNmZSGU6jpHpfQ6UXg79wThllkpSDdJ+7mKq9Usvx5+mWBgx0AmowW2hRFDYt354StUVAwIDAQAB";
	// 浙农小贷私钥
	private static final String ZN_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIy2ebskvbV6ghvOkckPwQ9Zk/U5lMutYUwaBSx18i643rhKoirGZy0en7HavZBAKtNsVqnjcjQ2phVqRj2dW1W5PKAH2thh+6yBW0nBgJdmXnenvzN3WR8ynhkSiyDSiLirBsqE4SZJOFYvXhlJowf6ZqMpj+uYq+8tqIL0eB09AgMBAAECgYARZCw/eRcJaw7RqQJTu2K9JvEmieteERvJcB3blOhN5gOA+5h3ePYfmX1bWhG71nJ0lxtO/BGNaoO0eeIck2pKSpp9IVbQ2PyoPlnQ+ZUtuEMMjuHfdvmbSYN/HfVvqfiOBlhYQxs5VkBKZbTm7H4r3WbvGSEEdla129R+qHP9cQJBANzLkLjJwlWdZ3mVrpdM5aPKcuie+ElzlUj5mSnCE6PQR7I/yEnevtQxzpIjso31VIbJVp4iGW7rOyPBVt591mMCQQCjJhnu/UuoEd+L8R2mJtJUsfRAQAPH14+hHbdckYB+/Xd0Nu8okX9NyLCjMScZkuOBLehKssu8bQHVPO6+CD/fAkAut9gAcZhwGyUo4dYWZ4vzk0OrGu/4Qr+kuEODRXB4afqxqbNMhhgkuAE4hNskFHP1LSbpwNdW0+kokKE75K53AkBrc12jLEnW0Ka1iUDovCrMw0NFyaNzzAH5sBOisOW7PX6eGMwcoO8CMMo9QNMqobaazrxt7iIG4JhTc8UdfVtrAkEAl0YIFBzfXR+bY/kLyD8IJWnAuG62RYr4lgTv5WZuNoVBcpXufl4wV/p7TRZW60xdjIvKvKDqXZAbG7zwjEQeIw==";
	// 明日私钥
	// private static final String ZN_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIzqhDYNl16RAK7kCtYXf0fBzxQ6nrD1VM6vkAxGcVxqB+jOYqoycBMouBSpgv7Ay576vqeOI71vxzjYkEAa/c0aWzv9HO7/KsfAyKNM2ZlIZTqOkel9DpReDv3BOGWWSlIN0n7uYqr1Sy/Hn6ZYGDHQCajBbaFEUNi3fnhK1RUDAgMBAAECgYBYfHby/kvb7Q/Rwo7vayfYpmHjYP//PL65XtMEckOC9W3sCZU5LJzl2v7ksqD4WZZDSU7pJj9ZGFSwZIUITCqsbibEuJ8hE2mYv63DQu4Td8x4JgzAqvX1u7jyV2giuDvP0us1+0trXFcufZXv5sO1R28Drs078dMxgS0EPyLeIQJBAM2CP9n47+sR4YR+YEvkrBBen/H8d5BW+VM89YecUh9GmiZzKX0S0bKtPWbgU9CRslpuHREMi880C8tP7Dz2slMCQQCviZ9FvBtHnOH4/dKBJo2FQyNG5o7u+939xXwO65tUI1Z4iC3ukB97GPMTej6swJ4VPbfhxAtK4R+Ur0G44xyRAkBurHxyiMu8Dq8iyWRWutmnHy6IBWzmgNBY8XVRCPB5A2600kQ/pUjlLsLoilrY8f3ZEX/51IXemqrXXT1eP4UdAkEArEvyCJm9UQpdB2/kWh5EVKR0kNgnPc38tGVOArsh6HETJBXOt9XBAKuZk+mucRRTVlgREEnEJ8hWnjNz2A0b8QJBALRsLbcg0wnj2V++RbgTN08OCm4AXggRnst+xvsOTwzCuih/C9urpcresQ3dQGiJP+weAmHTFOJvcAWfQpmGyFM=";

	/**
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
		} else {
			log.info("验签失败");
			throw new RuntimeException("验签未通过");
		}
		return request;
	}
}
