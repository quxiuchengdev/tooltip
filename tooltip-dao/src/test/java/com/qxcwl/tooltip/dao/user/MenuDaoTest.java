package com.qxcwl.tooltip.dao.user;

import com.qxcwl.tooltip.dao.menu.MenuDao;
import com.qxcwl.tooltip.model.menu.Menu;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author 曲修成
 * @ClassName MenuDaoTest
 * @Description
 * @Date 2016-11-29 17:47:00
 */
public class MenuDaoTest {


	MenuDao menuDao = null;

	@Before
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring/spring-context-db.xml");
		if (ctx == null) {
			throw new RuntimeException("读取spring信息错误");
		}
		menuDao = ctx.getBean("menuDao", MenuDao.class);
		if (menuDao == null) {
			throw new RuntimeException("用户Dao为空,读取错误");
		}
	}

	@Test
	public void listTest() {
		List<Menu> list = menuDao.findList(new Menu());
		System.out.println(list);
	}
}
