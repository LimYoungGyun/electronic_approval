package com.electronicapproval.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/sign_in_view")
	public String signInView() {
		
		return "user/sign_in";
	}
	
	@RequestMapping("/sign_out")
	public String signOut(HttpServletRequest reuqest) {
		HttpSession session = reuqest.getSession();
		session.invalidate();
		
		return "redirect:/user/sign_in_view";
	}
}
