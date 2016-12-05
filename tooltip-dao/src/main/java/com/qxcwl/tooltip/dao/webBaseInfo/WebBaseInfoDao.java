package com.qxcwl.tooltip.dao.webBaseInfo;

import com.qxcwl.tooltip.annotation.MyBatisDao;
import com.qxcwl.tooltip.dao.CrudDao;
import com.qxcwl.tooltip.model.webBaseInfo.WebBaseInfo;

/**
 * web基本信息数据库操作
 * @Author 曲修成
 * @ClassName WebBaseInfoDao
 * @Description
 * @Date 2016-11-17 11:38:00
 */
@MyBatisDao
public interface WebBaseInfoDao extends CrudDao<WebBaseInfo> {

	/**
	 * 获取已经启用的web信息
	 * @Title
	 * @Description
	 * @author 曲修成
	 * @date 2016-11-17 04:53:32
	 * @return WebBaseInfo
	 */
	public WebBaseInfo getEnabled();

}
