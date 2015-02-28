/**
 * SUNING APPLIANCE CHAINS.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.yeahwell.epu.common.crypt;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 功能描述: MD5加密工具类
 * 
 */
public class MD5Encrypt {

	private static Logger logger = LoggerFactory.getLogger(MD5Encrypt.class);

	private static final MD5Encrypt md5 = new MD5Encrypt();

	private MD5Encrypt() {

	}

	public static MD5Encrypt getInstance() {
		return md5;
	}

	/**
	 * 功能描述: 进行md5加密
	 * 
	 * @param context
	 *            加密内容
	 * @return 返回密文
	 */
	public String encrypt(String context) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(context.getBytes());
			// 处理摘要
			byte[] digesta = md5.digest(); // 加密

			return byte2String(digesta);
		} catch (Exception e) {
			logger.error("MD5Encrypt.encrypt({}): {}", context, e.getMessage());
		}

		return "";
	}

	/**
	 * 功能描述: 将字节数组转换成字符串
	 * 
	 * @param b
	 *            数组
	 * @return 字符串
	 */
	private String byte2String(byte[] b) {
		final int size = 1024;
		StringBuffer buf = new StringBuffer(size);
		String stmp = "";
		for (int i = 0; i < b.length; i++) {
			stmp = java.lang.Integer.toHexString(b[i] & 0XFF);
			if (stmp.length() == 1) {
				buf.append("0").append(stmp);
			} else {
				buf.append(stmp);
			}
		}

		return buf.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Encrypt.getInstance().encrypt("password"));
	}
}
