package com.electronicapproval.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	/**
	 * 로그인 페이지로 이동.
	 * @return
	 */
	@RequestMapping("/user/sign_in_view")
	public String signInView() {
		
		return "user/sign_in";
	}
	
	/**
	 * 로그아웃 후 로그인 페이지로 이동.
	 * @param reuqest
	 * @return
	 */
	@RequestMapping("/user/sign_out")
	public String signOut(HttpServletRequest reuqest) {
		HttpSession session = reuqest.getSession();
		session.invalidate();
		
		return "redirect:/user/sign_in_view";
	}
	
	/**
	 * 최초 로그인시에 비밀번호 변경 페이지로 이동.
	 * @return
	 */
	@RequestMapping("/password/password_change_view")
	public String passwordChage() {
		return "password/password_change";
	}
}
