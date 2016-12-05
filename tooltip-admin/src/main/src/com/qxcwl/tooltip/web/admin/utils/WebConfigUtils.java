package com.qxcwl.tooltip.web.admin.utils;

import com.qxcwl.tooltip.common.utils.PropertiesLoader;
import com.qxcwl.tooltip.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 曲修成
 * @ClassName WebConfigUtils
 * @Description
 * @Date 2016-11-17 17:56:00
 */
public class WebConfigUtils {


	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("/properties/tooltip.properties");

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<String, String>();

	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

	/**
	 * 获取网站管理目录
	 * @return
	 */
	public static String getAdminPath() {
		String adminPath = getConfig("adminPath");
		if (StringUtils.isNotEmpty(adminPath)) {
			if (!StringUtils.startsWith(adminPath, "/")) {
				adminPath = "/" +adminPath;
			}
		}else{
			adminPath = "";
		}
		return adminPath;
	}



}
