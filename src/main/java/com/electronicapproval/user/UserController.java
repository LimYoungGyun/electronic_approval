package com.electronicapproval.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@RequestMapping("/user/sign_in_view")
	public String signInView() {
		
		return "user/sign_in";
	}
	
	@RequestMapping("/user/sign_out")
	public String signOut(HttpServletRequest reuqest) {
		HttpSession session = reuqest.getSession();
		session.invalidate();
		
		return "redirect:/user/sign_in_view";
	}
	
	@RequestMapping("/password/password_change_view")
	public String passwordChage() {
		return "password/password_change";
	}
}
