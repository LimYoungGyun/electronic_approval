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
		Boolean useLogin = (Boolean) session.getAttribute("useLogin");
		
		// URL path를 가져온다.
		String uri = request.getRequestURI();
		
		if (employeeId != null && uri.startsWith("/user")) {
			// 만약 로그인이 되어 있으면 + /user => home 쪽으로 보낸다.
			if (useLogin != true) {
				response.sendRedirect("/password/password_change_view");
			} else {
				response.sendRedirect("/home/home_list_view");
			}
			return false;
		} else if ((employeeId != null && !uri.startsWith("/password") && useLogin == null) || (employeeId != null && !uri.startsWith("/password") && useLogin == false)) {
			// 비밀번호를 수정하지 않으면 수정페이지로 보낸다.
			response.sendRedirect("/password/password_change_view");
			return false;
		} else if (employeeId == null && uri.startsWith("/home")) {
			// 만약 로그인이 안되어 있으면 + /home => sign_in_view 쪽으로 보낸다.
			response.sendRedirect("/user/sign_in_view");
			return false;
		} else if (employeeId == null && uri.startsWith("/post")) {
			// 만약 로그인이 안되어 있으면 + /post => sign_in_view 쪽으로 보낸다.
			response.sendRedirect("/user/sign_in_view");
			return false;
		} else if (employeeId == null && uri.startsWith("/group")) {
			// 만약 로그인이 안되어 있으면 + /group => sign_in_view 쪽으로 보낸다.
			response.sendRedirect("/user/sign_in_view");
			return false;
		} else if (employeeId == null && uri.startsWith("/employee")) {
			// 만약 로그인이 안되어 있으면 + /employee => sign_in_view 쪽으로 보낸다.
			response.sendRedirect("/user/sign_in_view");
			return false;
		} else if (employeeId == null && uri.startsWith("/commute")) {
			// 만약 로그인이 안되어 있으면 + /employee => sign_in_view 쪽으로 보낸다.
			response.sendRedirect("/user/sign_in_view");
			return false;
		} else if (employeeId == null && uri.startsWith("/form")) {
			// 만약 로그인이 안되어 있으면 + /employee => sign_in_view 쪽으로 보낸다.
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
