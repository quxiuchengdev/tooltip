package com.qxcwl.tooltip.web.admin.utils;

import com.qxcwl.tooltip.common.utils.SpringContextHolder;
import com.qxcwl.tooltip.model.webBaseInfo.WebBaseInfo;
import com.qxcwl.tooltip.service.webBaseInfo.WebBaseInfoService;

/**
 * 网站基础信息工具类
 * @Author 曲修成
 * @ClassName WebBaseInfoUtils
 * @Description
 * @Date 2016-11-17 15:45:00
 */
public class WebBaseInfoUtils {

	private static WebBaseInfoService webBaseInfoService = SpringContextHolder.getBean(WebBaseInfoService.class);

	/**
	 * 获取产品名称
	 * @Title getProductName
	 * @Description
	 * @author 曲修成
	 * @date 2016-11-17 04:55:51
	 * @param
	 * @return java.lang.String
	 */
	public static String getProductName(){
		WebBaseInfo webBaseInfo = webBaseInfoService.getEnabled();
		if (webBaseInfo == null) {
			return "";
		}else{
			return webBaseInfo.getProductName();
		}
	}

	/**
	 * 获取公司名称
	 * @Title getCompany
	 * @Description
	 * @author 曲修成
	 * @date 2016-11-17 04:55:51
	 * @param
	 * @return java.lang.String
	 */
	public static String getCompany(){
		WebBaseInfo webBaseInfo = webBaseInfoService.getEnabled();
		if (webBaseInfo == null) {
			return "";
		}else{
			return webBaseInfo.getCompany();
		}
	}


}
