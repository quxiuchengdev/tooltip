package com.qxcwl.tooltip.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author 曲修成
 * @ClassName EqualsUtils
 * @Description
 * @Date 2016-11-04 15:00:00
 */
public class EqualsUtils {

	private final static Logger logger = LoggerFactory.getLogger(EqualsUtils.class);

	public static boolean Equals(Object source, Object target) {
		if (source == null || target == null) {
			return false;
		}
		boolean rv = true;
		if (source instanceof Map) {
			rv = mapOfSrc(source, target, rv);
		} else {
			rv = classOfSrc(source, target, rv);
		}
		//logger.info("THE EQUALS RESULT IS " + rv);
		return rv;
	}

	/**
	 * 源目标为MAP类型时
	 * @param source
	 * @param target
	 * @param rv
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static boolean mapOfSrc(Object source, Object target, boolean rv) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = (HashMap<String, String>) source;
		for (String key : map.keySet()) {
			if (target instanceof Map) {
				HashMap<String, String> tarMap = new HashMap<String, String>();
				tarMap = (HashMap<String, String>) target;
				if(tarMap.get(key)==null){
					rv = false;
					break;
				}
				if (!map.get(key).equals(tarMap.get(key))) {
					rv = false;
					break;
				}
			} else {
				String tarValue = getClassValue(target, key) == null ? "" : getClassValue(target, key).toString();
				if (!tarValue.equals(map.get(key))) {
					rv = false;
					break;
				}
			}
		}
		return rv;
	}

	/**
	 * 源目标为非MAP类型时
	 * @param source
	 * @param target
	 * @param rv
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static boolean classOfSrc(Object source, Object target, boolean rv) {
		Class<?> srcClass = source.getClass();
		Field[] fields = srcClass.getDeclaredFields();
		for (Field field : fields) {
			String nameKey = field.getName();
			if (target instanceof Map) {
				HashMap<String, String> tarMap = new HashMap<String, String>();
				tarMap = (HashMap<String, String>) target;
				String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey)
						.toString();
				if(tarMap.get(nameKey)==null){
					rv = false;
					break;
				}
				if (!tarMap.get(nameKey).equals(srcValue)) {
					rv = false;
					break;
				}
			} else {
				String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey)
						.toString();
				String tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey)
						.toString();
				if (!srcValue.equals(tarValue)) {
					rv = false;
					break;
				}
			}
		}
		return rv;
	}

	/**
	 * 根据字段名称取值
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getClassValue(Object obj, String fieldName) {
		if (obj == null) {
			return null;
		}
		try {
			Class<? extends Object> beanClass = obj.getClass();
			Method[] ms = beanClass.getMethods();
			for (int i = 0; i < ms.length; i++) {
				// 非get方法不取
				if (!ms[i].getName().startsWith("get")) {
					continue;
				}
				Object objValue = null;
				try {
					objValue = ms[i].invoke(obj, new Object[] {});
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("反射取值出错：" + e.toString());
					continue;
				}
				if (objValue == null) {
					continue;
				}
				if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
						|| ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
					return objValue;
				} else if (fieldName.toUpperCase().equals("SID")
						&& (ms[i].getName().toUpperCase().equals("ID") || ms[i].getName().substring(3).toUpperCase()
						.equals("ID"))) {
					return objValue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("取方法出错！" + e.toString());
		}
		return null;
	}

}
