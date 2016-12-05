package com.qxcwl.tooltip.service.user;

import com.qxcwl.tooltip.model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * @Author 曲修成
 * @ClassName UserServiceTest
 * @Description
 * @Date 2016-11-05 15:59:00
 */
public class UserServiceTest {

	UserService userService = null;



	@Before
	public void init(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:/spring/spring-context.xml","classpath:/spring/spring-context-db.xml");
		if (ctx == null) {
			throw new RuntimeException("读取spring信息错误");
		}
		userService = (UserService) ctx.getBean("userService");
		if (userService == null) {
			throw new RuntimeException("用户userService为空,读取错误");
		}
	}

	@Test
	public void listTest() {
		List<User> list = userService.findList(new User());
		System.out.println(list);
	}
	@Test
	public void updateTest(){
//		User user = userService.get("1");
//		System.out.println(user);
//		user.setIdCard("111");
//		userService.update(user);
//		//int i = 1/0;
//		System.out.println(user);
	}


}
