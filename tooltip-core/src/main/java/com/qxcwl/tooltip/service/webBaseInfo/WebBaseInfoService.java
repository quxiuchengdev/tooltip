package com.qxcwl.tooltip.service.webBaseInfo;

import com.qxcwl.tooltip.dao.webBaseInfo.WebBaseInfoDao;
import com.qxcwl.tooltip.model.webBaseInfo.WebBaseInfo;
import com.qxcwl.tooltip.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 曲修成
 * @ClassName WebBaseInfoService
 * @Description
 * @Date 2016-11-17 14:08:00
 */
@Service
@Transactional(readOnly = true)
public class WebBaseInfoService extends CrudService<WebBaseInfoDao, WebBaseInfo> {


	/**
	 * 获取已经启用的web信息
	 * @Title getEnabled
	 * @Description
	 * @author 曲修成
	 * @date 2016-11-17 04:54:35
	 * @param
	 * @return com.qxcwl.tooltip.model.webBaseInfo.WebBaseInfo
	 */
	public WebBaseInfo getEnabled(){
		return dao.getEnabled();
	}

}
