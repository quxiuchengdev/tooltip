package com.qxcwl.tooltip.web.admin.login;

import com.qxcwl.tooltip.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 曲修成
 * @ClassName LoginController
 * @Description
 * @Date 2016-11-15 11:03:00
 */
@Controller
public class LoginController  extends BaseController{

	@RequestMapping(value = "${adminPath}/login")
	public String login(){
		return "login/login";
	}
}
