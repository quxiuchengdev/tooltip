package com.qxcwl.tooltip.common.utils;

import java.security.MessageDigest;

/**
 * @Author 曲修成
 * @ClassName Md5Utils
 * @Description
 * @Date 2016-11-04 14:57:00
 */
public class Md5Utils {

	/**
	 * 获得MD5加密密码的方法_大写
	 */
	public static String getMD5ofStrUp(String origString) {
		return getMD5ofStrLow(origString).toUpperCase();
	}

	/**
	 * 获得MD5加密密码的方法_小写
	 */
	public static String getMD5ofStrLow(String origString) {
		String origMD5 = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] result = md5.digest(origString.getBytes("UTF-8"));
			origMD5 = byteArray2HexStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origMD5;
	}

	/**
	 * 处理字节数组得到MD5密码的方法
	 */
	private static String byteArray2HexStr(byte[] bs) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bs) {
			sb.append(byte2HexStr(b));
		}
		return sb.toString();
	}

	/**
	 * 字节标准移位转十六进制方法
	 */
	private static String byte2HexStr(byte b) {
		String hexStr = null;
		int n = b;
		if (n < 0) {
			// 若需要自定义加密,请修改这个移位算法即可
			n = b & 0x7F + 128;
		}
		hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
		return hexStr;
	}

	/**
	 * 提供一个MD5多次加密方法
	 */
	public static String getMD5ofStr(String origString, int times) {
		String md5 = getMD5ofStrLow(origString);
		for (int i = 0; i < times - 1; i++) {
			md5 = getMD5ofStrLow(md5);
		}
		return getMD5ofStrLow(md5);
	}

	/**
	 * 密码验证方法
	 */
	public static boolean verifyPassword(String inputStr, String MD5Code) {
		return getMD5ofStrLow(inputStr).equals(MD5Code);
	}

	/**
	 * 多次加密时的密码验证方法
	 */
	public static boolean verifyPassword(String inputStr, String MD5Code,
										 int times) {
		return getMD5ofStr(inputStr, times).equals(MD5Code);
	}

}
