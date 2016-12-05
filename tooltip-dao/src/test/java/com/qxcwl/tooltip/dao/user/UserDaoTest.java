package com.qxcwl.tooltip.dao.user;

import com.qxcwl.tooltip.model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * @Author 曲修成
 * @ClassName UserDaoTest
 * @Description
 * @Date 2016-11-05 8:36:00
 */
public class UserDaoTest {

	UserDao userDao = null;

	@Before
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring/spring-context-db.xml");
		if (ctx == null) {
			throw new RuntimeException("读取spring信息错误");
		}
		userDao = ctx.getBean("userDao", UserDao.class);
		if (userDao == null) {
			throw new RuntimeException("用户Dao为空,读取错误");
		}
	}

	@Test
	public void listTest() {
		List<User> list = userDao.findList(new User());
		System.out.println(list);
	}

	@Test
	public void updateTest(){
//		User user = userDao.get("1");
//		System.out.println(user);
//		user.setIdCard("2");
//
//		userDao.update(user);
//		int i = 1/0;
//		System.out.println(user);
	}


}
