package com.hancy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 * 一个拦截器会拦截一个请求三次，分别是方法处理前，方法处理后，页面渲染后
 */
public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		System.out.println("方法前");
		
		//获取请求的路径，如/login.action
		String requestURI = request.getRequestURI();
		//如果请求的是非登录页面
		if(!requestURI.contains("/login")){
			//获取请求中的session:USER_SESSION
			Object username = request.getSession().getAttribute("USER_SESSION");
			//如果USER_SESSION等于空说明没有登录
			if(username == null){
				//转发到登录页面
				response.sendRedirect(request.getContextPath() + "/login.action");
				//打回
				return false;
			}
		}
		//放行
		return true;
	}
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

		System.out.println("方法后");
		
	}
	
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

		System.out.println("页面渲染后");
		
	}



}
