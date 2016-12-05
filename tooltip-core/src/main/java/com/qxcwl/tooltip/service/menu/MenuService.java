package com.qxcwl.tooltip.service.menu;


import com.qxcwl.tooltip.service.CrudService;
import com.qxcwl.tooltip.dao.menu.MenuDao;
import com.qxcwl.tooltip.model.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuService extends CrudService<MenuDao, Menu> {

	@Autowired
	private MenuDao menuDao;
	
	@Transactional(readOnly = false)
	public void save(Menu menu){
		//menu.setId(IdGen.uuid());
		//menu.setDelFlag(delFlag);
	}
	
}
