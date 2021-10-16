package com.electronicapproval.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		Integer employeeId = (Integer) session.getAttribute("employeeId");
		
		// URL path를 가져온다.
		String uri = request.getRequestURI();
		
		if (employeeId != null && uri.startsWith("/user")) {
			// 만약 로그인이 되어 있으면 + /user => post 쪽으로 보낸다.
			response.sendRedirect("/home/home_list_view");
			return false;
		} else if (employeeId == null && uri.startsWith("/home")) {
			// 만약 로그인이 안되어 있으면 + /post => user 쪽으로 보낸다.
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
				
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
	
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
		
	}
}
