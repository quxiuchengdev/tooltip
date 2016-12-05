package com.qxcwl.tooltip.common.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.Map;

/**
 * @Author 曲修成
 * @ClassName HmacSHA256Utils
 * @Description
 * @Date 2016-11-04 15:26:00
 */
public class HmacSHA256Utils {

	/**
	 * 使用指定的密码对内容生成消息摘要（散列值)
	 * @Title digest
	 * @Description
	 * @author 曲修成
	 * @date 2016年5月21日 下午2:22:27
	 * @param key
	 * @param content
	 * @return
	 */
	public static String digest(String key, String content) {
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			byte[] secretByte = key.getBytes("utf-8");
			byte[] dataBytes = content.getBytes("utf-8");

			SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");
			mac.init(secret);

			byte[] doFinal = mac.doFinal(dataBytes);
			byte[] hexB = new Hex().encode(doFinal);
			return new String(hexB, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * //使用指定的密码对整个Map的内容生成消息摘要（散列值）
	 *
	 * @Title digest
	 * @Description
	 * @author 曲修成
	 * @date 2016年5月21日 下午2:22:40
	 * @param key
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String digest(String key, Map<String, ?> map) {
		StringBuilder s = new StringBuilder();
		for (Object values : map.values()) {
			if (values instanceof String[]) {
				for (String value : (String[]) values) {
					s.append(value);
				}
			} else if (values instanceof List) {
				for (String value : (List<String>) values) {
					s.append(value);
				}
			} else {
				s.append(values);
			}
		}
		return digest(key, s.toString());
	}

}
