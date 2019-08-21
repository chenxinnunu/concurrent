package com.chenxin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


/**
 * @author 未知 2018-09-07
 */
public class DigitalSignUtils {

	private static final Logger log = LoggerFactory.getLogger(DigitalSignUtils.class);

	private static final String RSA = "RSA";

	/**
	 * SHA1WithRSA数字加签验签模式
	 */
	public static final String SHA1WITHRSA = "SHA1WithRSA";
	/**
	 * SHA256WithRSA数字加签验签模式
	 */
	public static final String SHA256WITHRSA = "SHA256WithRSA";
	/**
	 * MD5WithRSA数字加签验签模式
	 */
	public static final String MD5WITHRSA = "MD5WithRSA";
	/**
	 * MD2withRSA数字加签验签模式
	 */
	public static final String MD2WITHRSA = "MD2WithRSA";

	/**
	 * 通过传入内容添加数字签名
	 * @param content 传入内容,默认进行base64解密
	 * @param privateKey 加载后的密钥
	 * @param modelCode 传入加密模式
	 * @return
	 * @throws Exception
	 */
	public static String addDigitalSign(String content, String privateKey, String modelCode) {
		byte[] signs = null;
		try {
			byte[] contentBytes = Base64.getDecoder().decode(content);
			Signature signature = Signature.getInstance(modelCode);
			signature.initSign(loadPrivateKey(privateKey));
			signature.update(contentBytes);
			signs = signature.sign();
		} catch (Exception e) {
			log.error("内容加签是报出异常,加签失败", e);
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(signs);
	}
	
	/**
	 * 通过传入内容添加数字签名
	 * @param content 进过base64解密的或未解密的数组
	 * @param privateKey 加载后的密钥
	 * @param modelCode 传入加密模式
	 * @return
	 */
	public static String addDigitalSign(byte[] content, String privateKey, String modelCode) {
        byte[] signs = null;
        try {
            Signature signature = Signature.getInstance(modelCode);
            signature.initSign(loadPrivateKey(privateKey));
            signature.update(content);
            signs = signature.sign();
        } catch (Exception e) {
            log.error("内容加签是报出异常,加签失败", e);
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(signs);
    }

	/**
	 * 通过传入的内容和签名进行数字验签
	 * @param content 传入的内容,默认进行base64解密
	 * @param sign 传入的签名串
	 * @param publicKey 加载后的公钥
	 * @param modelCode 验签模式
	 * @return
	 */
	public static boolean verifyDigitalSign(String content, String sign, String publicKey, String modelCode) {
		Signature signature = null;
		boolean flag = false;
		try {
			byte[] contentBytes = Base64.getDecoder().decode(content);
			signature = Signature.getInstance(modelCode);
			signature.initVerify(loadPublicKey(publicKey));
			signature.update(contentBytes);
			flag = signature.verify(Base64.getDecoder().decode(sign));
		} catch (Exception e) {
			log.error("内容加签是报出异常,验签失败", e);
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
     * 通过传入的内容和签名进行数字验签
     * @param content 传入的内容,经过base64解密或为经过base64解密的字符数组
     * @param sign 传入的签名串,经过base64解密或为经过base64解密的字符数组
     * @param publicKey 加载后的公钥
     * @param modelCode 验签模式
     * @return
     */
    public static boolean verifyDigitalSign(byte[] content, byte[] sign, String publicKey, String modelCode) {
        Signature signature = null;
        boolean flag = false;
        try {
            signature = Signature.getInstance(modelCode);
            signature.initVerify(loadPublicKey(publicKey));
            signature.update(content);
            flag = signature.verify(sign);
        } catch (Exception e) {
            log.error("内容加签是报出异常,验签失败", e);
            e.printStackTrace();
        }
        return flag;
    }

	/**
	 * RSA加签私钥加载
	 * @param privateKey
	 * @return
	 */
	public static PrivateKey loadPrivateKey(String privateKey) {
		PrivateKey priKey = null;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
			priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priKey;
	}

	/**
	 * RSA验签公钥加载
	 * @param publicKey
	 * @return
	 */
	public static PublicKey loadPublicKey(String publicKey) {
		PublicKey pubKey = null;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
			pubKey = keyFactory.generatePublic(x509KeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pubKey;
	}
}