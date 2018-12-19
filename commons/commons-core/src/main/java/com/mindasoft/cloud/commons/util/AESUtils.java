package com.mindasoft.cloud.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * AES加密工具类 
 * 可实现字符串加密和解密,采用AES加密算法
 * @author suxl
 * @date 2013/06/27
 * 
 */
public class AESUtils {

	protected static Logger log = LoggerFactory.getLogger(AESUtils.class);

	public final static String URL_KEY = "f5253d3e727685745c59b697babcc080"; // url加密key

	/**
	 * Url 加密
	 * 
	 * @param argStr
	 *            参数字符串
	 * @return
	 */
	public static String encode(String argStr) {
		String result = null;
		try {
			byte[] byteRe = enCrypt(argStr, URL_KEY);
			result = parseByte2HexStr(byteRe);
		} catch (Exception e) {
			log.error("Url加密失败!", e);
		}
		return result;
	}

	/**
	 * Url 解密
	 * 
	 * @param encrytStr
	 *            加密的参数字符串
	 * @return
	 */
	public static String decode(String encrytStr) {
		String result = null;
		try {
			byte[] encrytByte = parseHexStr2Byte(encrytStr);
			result = deCrypt(encrytByte, URL_KEY);
		} catch (Exception e) {
			log.error("Url解密失败!", e);
		}
		return result;
	}

	/**
	 * 加密函数
	 * 
	 * @param content
	 *            加密的内容
	 * @param strKey
	 *            密钥
	 * @return 返回二进制字符数组
	 * @throws Exception
	 */
	public static byte[] enCrypt(String content, String strKey)
			throws Exception {
		byte[] cByte = null;
		try {
			KeyGenerator keygen;
			SecretKey desKey;
			Cipher c;
			String str = content;
			keygen = KeyGenerator.getInstance("AES");
			keygen.init(128, new SecureRandom(strKey.getBytes()));
			desKey = keygen.generateKey();
			c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, desKey);
			cByte = c.doFinal(str.getBytes("UTF-8"));
		} catch (Exception e) {
			log.error("AES加密操作失败!", e);
		}
		return cByte;
	}

	/**
	 * 解密函数
	 * 
	 * @param src
	 *            加密过的二进制字符数组
	 * @param strKey
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static String deCrypt(byte[] src, String strKey) throws Exception {
		byte[] cByte = null;
		try {
			KeyGenerator keygen;
			SecretKey desKey;
			Cipher c;
			keygen = KeyGenerator.getInstance("AES");
			keygen.init(128, new SecureRandom(strKey.getBytes()));
			desKey = keygen.generateKey();
			c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, desKey);
			cByte = c.doFinal(src);
		} catch (Exception e) {
			log.error("AES解密失败!", e);
		}
		return new String(cByte, "UTF-8");
	}

	/**
	 * 将2进制转化成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < buf.length; i++) {
				String hex = Integer.toHexString(buf[i] & 0xFF);
				if (hex.length() == 1) {
					hex = '0' + hex;
				}
				sb.append(hex.toUpperCase());
			}
		} catch (Exception e) {
			log.error("将2进制转化成16进制失败!", e);
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
}
