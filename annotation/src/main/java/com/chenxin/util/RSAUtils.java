package com.chenxin.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


/**
 * RSA加密或加签
 * @author yangcj 2018-09-29
 */
public final class RSAUtils {

	/**
	 * 获取cipher对象中的常量,常规RSA
	 */
	public static final String RSA = "RSA";
	/**
	 * 获取cipher对象中的常量,百度用RSA
	 */
	public static final String RSA_ECB_PKCS1 = "RSA/ECB/PKCS1Padding";
	/**
	 * 字符集
	 */
	private static final String CHAR_SET = "UTF-8";
	
	private static BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
	/**
	 * 通过自动生成键值,常用于管理台防拦截
	 */
	public static KeyPair KEY_PAIR = generateRSAKeyPair();

	/**
	 * 随机生成RSA密钥对(默认密钥长度为1024)
	 * @return
	 */
	public static KeyPair generateRSAKeyPair() {
		if (KEY_PAIR == null) {
			return generateRSAKeyPair(1024, bouncyCastleProvider);
		}
		return KEY_PAIR;
	}

	/**
	 * 随机生成RSA密钥对
	 * @param keyLength 密钥长度，范围：512～2048 一般1024
	 * @return
	 */
	public static KeyPair generateRSAKeyPair(final int keyLength) {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
			kpg.initialize(keyLength);
			return kpg.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static KeyPair generateRSAKeyPair(final int keyLength, BouncyCastleProvider bouncyCastleProvider) {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA, bouncyCastleProvider);
			keyPairGen.initialize(keyLength, new SecureRandom());
			KeyPair keyPair = keyPairGen.generateKeyPair();
			return keyPair;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取公钥模值n
	 * @param publicKey
	 * @return
	 */
	public static int getKeySize(PublicKey publicKey) {
		RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		return rsaPublicKey.getModulus().bitLength();
	}

	/**
	 * 获取私钥模值n
	 * @param privateKey
	 * @return
	 */
	public static int getKeySize(PrivateKey privateKey) {
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
		return rsaPrivateKey.getModulus().bitLength();
	}

	/**
	 * 返回模值n
	 */
	public static String getModulus() {
		RSAPublicKey rsaPublicKey = (RSAPublicKey) KEY_PAIR.getPublic();
		BigInteger b = rsaPublicKey.getModulus();
		return b.toString(16);
	}

	/**
	 * 返回公钥指数e
	 */
	public static String getPublicExponent() {
		RSAPublicKey rsaPublicKey = (RSAPublicKey) KEY_PAIR.getPublic();
		BigInteger b = rsaPublicKey.getPublicExponent();
		return b.toString(16);
	}

	/**
	 * 返回私钥指数d
	 * @return
	 */
	public static String getPrivateExponent() {
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KEY_PAIR.getPrivate();
		BigInteger b = rsaPrivateKey.getPrivateExponent();
		return b.toString(16);
	}

	/**
	 * 用公钥加密 <br>
	 * 每次加密的字节数，不能超过密钥的长度值减去11
	 * @param data 需加密数据的byte数据
	 * @param publicKey 公钥
	 * @return 加密后的byte型数据
	 */
	public static String encryptData(String data, String publicKey, String encodeModel) {
		try {
			byte[] encryptData = data.getBytes();
			// 编码前设定编码方式及密钥
			Cipher cipher = Cipher.getInstance(encodeModel);
			PublicKey pubKey = loadPublicKey(publicKey);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			int keyBit = getKeySize(pubKey);
			int inputLen = encryptData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			int step = keyBit / 8 - 11;

			for (int i = 0; inputLen - offSet > 0; offSet = i * step) {
				byte[] cache;
				if (inputLen - offSet > step) {
					cache = cipher.doFinal(encryptData, offSet, step);
				} else {
					cache = cipher.doFinal(encryptData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				++i;
			}

			byte[] encryptedData = out.toByteArray();
			out.close();
			return Base64.getEncoder().encodeToString(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用内置公钥进行解密
	 * @param data
	 * @return
	 */
	public static byte[] encryptData(byte[] data) {
		PublicKey publicKey = KEY_PAIR.getPublic();
		try {
			Cipher cipher = Cipher.getInstance(RSA, bouncyCastleProvider);
			// 编码前设定编码方式及密钥
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			int keyBit = getKeySize(publicKey);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			int step = keyBit / 8 - 11;

			for (int i = 0; inputLen - offSet > 0; offSet = i * step) {
				byte[] cache;
				if (inputLen - offSet > step) {
					cache = cipher.doFinal(data, offSet, step);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}

				out.write(cache, 0, cache.length);
				++i;
			}

			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用私钥解密
	 * @param privateKey 传入经过处理的私钥类(可使用loadPrivateKey处理)
	 * @param encodeModel 传入密钥编码表
	 * @return
	 */
	public static String decryptData(String data, String privateKey, String encodeModel) {
		try {
			byte[] encryptedData = Base64.getDecoder().decode(data);
			Cipher cipher = Cipher.getInstance(encodeModel);
			PrivateKey priKey = loadPrivateKey(privateKey);
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			int keyBit = getKeySize(priKey);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			int step = keyBit / 8;

			for (int i = 0; inputLen - offSet > 0; offSet = i * step) {
				byte[] cache;
				if (inputLen - offSet > step) {
					cache = cipher.doFinal(encryptedData, offSet, step);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}

				out.write(cache, 0, cache.length);
				++i;
			}

			byte[] decryptedData = out.toByteArray();
			out.close();
			return new String(decryptedData, CHAR_SET);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 使用内置私钥进行解密
	 * @return
	 */
	public static String decryptData(byte[] data) {
		PrivateKey privateKey = KEY_PAIR.getPrivate();
		try {
			Cipher cipher = Cipher.getInstance(RSA, bouncyCastleProvider);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			int keyBit = getKeySize(privateKey);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			int step = keyBit / 8;

			for (int i = 0; inputLen - offSet > 0; offSet = i * step) {
				byte[] cache;
				if (inputLen - offSet > step) {
					cache = cipher.doFinal(data, offSet, step);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}

				out.write(cache, 0, cache.length);
				++i;
			}

			byte[] decryptedData = out.toByteArray();
			out.close();
			String reverseDecryptedString = new StringBuffer(new String(decryptedData)).reverse().toString();
			String decryptpwd = URLDecoder.decode(reverseDecryptedString, "UTF-8");
			return decryptpwd;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
	 * @param keyBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 通过私钥byte[]将公钥还原，适用于RSA算法
	 * @param keyBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 使用N、e值还原公钥
	 * @param modulus
	 * @param publicExponent
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(String modulus, String publicExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		BigInteger bigIntModulus = new BigInteger(modulus);
		BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 使用N、d值还原私钥
	 * @param modulus
	 * @param privateExponent
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(String modulus, String privateExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		BigInteger bigIntModulus = new BigInteger(modulus);
		BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 从字符串中加载公钥
	 * @param publicKeyStr 公钥数据字符串
	 * @throws Exception 加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64.getDecoder().decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 从字符串中加载私钥<br>
	 * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
	 * @param privateKeyStr
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
		try {
			byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	/**
	 * 从文件中输入流中加载公钥
	 * @param in 公钥输入流
	 * @throws Exception 加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(InputStream in) throws Exception {
		try {
			return loadPublicKey(readKey(in));
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 * 从文件中加载私钥
	 * @return 是否成功
	 * @throws Exception
	 */
	public static PrivateKey loadPrivateKey(InputStream in) throws Exception {
		try {
			return loadPrivateKey(readKey(in));
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}

	/**
	 * 读取密钥信息
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static String readKey(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String readLine = null;
		StringBuilder sb = new StringBuilder();
		while ((readLine = br.readLine()) != null) {
			if (readLine.charAt(0) == '-') {
				continue;
			} else {
				sb.append(readLine);
				sb.append('\r');
			}
		}
		return sb.toString();
	}



	/**
	 * 打印公钥信息
	 * @param publicKey
	 */
	public static void printPublicKeyInfo(PublicKey publicKey) {
		RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		System.out.println("----------RSAPublicKey----------");
		System.out.println("Modulus.length=" + rsaPublicKey.getModulus().bitLength());
		System.out.println("Modulus=" + rsaPublicKey.getModulus().toString());
		System.out.println("PublicExponent.length=" + rsaPublicKey.getPublicExponent().bitLength());
		System.out.println("PublicExponent=" + rsaPublicKey.getPublicExponent().toString());
	}

	public static void printPrivateKeyInfo(PrivateKey privateKey) {
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
		System.out.println("----------RSAPrivateKey ----------");
		System.out.println("Modulus.length=" + rsaPrivateKey.getModulus().bitLength());
		System.out.println("Modulus=" + rsaPrivateKey.getModulus().toString());
		System.out.println("PrivateExponent.length=" + rsaPrivateKey.getPrivateExponent().bitLength());
		System.out.println("PrivatecExponent=" + rsaPrivateKey.getPrivateExponent().toString());

	}

	public static void main(String[] args) throws Exception {
		KeyPair keyPair = generateRSAKeyPair(1024);

		System.out.println("私钥:" + Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
		System.out.println("公钥:" + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
	}
}