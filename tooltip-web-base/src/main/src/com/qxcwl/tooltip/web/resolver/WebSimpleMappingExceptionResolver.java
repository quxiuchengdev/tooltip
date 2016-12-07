package com.qxcwl.tooltip.web.resolver;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 曲修成
 * @ClassName SimpleMappingExceptionResolver
 * @Description
 * @Date 2016-12-06 15:26:00
 */
public class WebSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {


	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ex.printStackTrace();
		return super.doResolveException(request, response, handler, ex);
	}
}
