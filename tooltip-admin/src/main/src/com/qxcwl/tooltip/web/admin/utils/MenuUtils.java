package com.qxcwl.tooltip.web.admin.utils;

import com.qxcwl.tooltip.common.utils.SpringContextHolder;
import com.qxcwl.tooltip.dao.menu.MenuDao;
import com.qxcwl.tooltip.model.menu.Menu;

import java.util.List;

/**
 * @Author 曲修成
 * @ClassName MenuUtils
 * @Description
 * @Date 2016-11-29 17:30:00
 */
public class MenuUtils {


	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);

	/**
	 * 获取树菜单
	 * @return
	 */
	public static List<Menu> getChildTreeMenuList(){
		List<Menu> menuList = menuDao.findList(new Menu());

		menuList = Menu.getTreeMenuList(menuList);
		return menuList;
	}

}
