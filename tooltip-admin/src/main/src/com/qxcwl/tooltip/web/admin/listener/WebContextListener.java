package com.qxcwl.tooltip.web.admin.listener;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Author 曲修成
 * @ClassName WebContextListener
 * @Description
 * @Date 2016-11-11 11:38:00
 */
public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		//加载使用信息
		StringBuilder sb = new StringBuilder();
		sb.append("                                             \n");
		sb.append("                   _ooOoo_                   \n");
		sb.append("                  o8888888o                  \n");
		sb.append("                  88\" . \"88                  \n");
		sb.append("                  (| -_- |)                  \n");
		sb.append("                  O\\  =  /O                  \n");
		sb.append("               ____/`---'\\____               \n");
		sb.append("             .'  \\\\|     |//  `.             \n");
		sb.append("            /  \\\\|||  :  |||//  \\            \n");
		sb.append("           /  _||||| -:- |||||-  \\           \n");
		sb.append("           |   | \\\\\\  -  /// |   |           \n");
		sb.append("           | \\_|  ''\\---/''  |   |           \n");
		sb.append("           \\  .-\\__  `-`  ___/-. /           \n");
		sb.append("         ___`. .'  /--.--\\  `. . __          \n");
		sb.append("      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".       \n");
		sb.append("     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |     \n");
		sb.append("     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /     \n");
		sb.append("======`-.____`-.___\\_____/___.-`____.-'======\n");
		sb.append("                   `=---='                   \n");
		sb.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
		sb.append("         佛祖保佑       永无BUG               \n");
		sb.append("                                             \n");
		System.out.println(sb);
		return super.initWebApplicationContext(servletContext);
	}
}
