/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.util.AES.java
 *
 * @author andy
 * @date 2016年11月20日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密工具类
 * com.bonc.spider.util.AES.java
 * 
 * @author andy
 * @date 2016年11月20日
 *
 * @since 0.0.1
 */
public class AES {
	
	public static final String DEFAULT_ENCODING = "utf-8";
	
	/**
	 * 加密
	 * 
	 * @param data 需要加密的内容
	 * @param secretKey 加密秘钥
	 * @return String
	 */
	public static String encrypt(String data, String secretKey) {
		try {
			byte[] input = data.getBytes(DEFAULT_ENCODING);
	
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(secretKey.getBytes(DEFAULT_ENCODING));
			SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skc);
	
			byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
			int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
			ctLength += cipher.doFinal(cipherText, ctLength);
			return parseByte2HexStr(cipherText);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (ShortBufferException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param data 待解密内容
	 * @param secretKey 解密密钥
	 * @return byte[]
	 */
	public static String decrypt(String data, String secretKey) {
		try {
			byte[] keyb = secretKey.getBytes(DEFAULT_ENCODING);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(keyb);
			SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
			Cipher dcipher = Cipher.getInstance("AES");
			dcipher.init(Cipher.DECRYPT_MODE, skey);
	
			byte[] clearbyte = dcipher.doFinal(parseHexStr2Byte(data));
			return new String(clearbyte);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return String
	 */
	private static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	
	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return byte[]
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		int len = hexStr.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexStr.substring(2 * i, 2 * i + 2), 16).byteValue();
		}
		return result;
	}
}
