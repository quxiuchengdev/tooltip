package com.qxcwl.tooltip.model.webBaseInfo;

import com.qxcwl.tooltip.model.DataEntity;

/**
 * 网站基本信息
 * @Author 曲修成
 * @ClassName WebBaseInfo
 * @Description
 * @Date 2016-11-17 11:28:00
 */
public class WebBaseInfo extends DataEntity<WebBaseInfo> {

	private static final long serialVersionUID = 1347783992761825237L;


	/**
	 * 产品信息
	 */
	private String productName;

	/**
	 * 版权年限
	 */
	private String copyRightYear;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 公司名称
	 */
	private String company;

	/**
	 * 是否启用
	 */
	private boolean enable;

	/**
	 * 获取 产品信息
	 * @return productName 产品信息
	 */
	public String getProductName() {
		return this.productName;
	}

	/**
	 * 设置 产品信息
	 * @param productName 产品信息
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 获取 版权年限
	 * @return copyRightYear 版权年限
	 */
	public String getCopyRightYear() {
		return this.copyRightYear;
	}

	/**
	 * 设置 版权年限
	 * @param copyRightYear 版权年限
	 */
	public void setCopyRightYear(String copyRightYear) {
		this.copyRightYear = copyRightYear;
	}

	/**
	 * 获取 版本号
	 * @return version 版本号
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * 设置 版本号
	 * @param version 版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 获取 公司名称
	 * @return company 公司名称
	 */
	public String getCompany() {
		return this.company;
	}

	/**
	 * 设置 公司名称
	 * @param company 公司名称
	 */
	public void setCompany(String company) {
		this.company = company;
	}
}
