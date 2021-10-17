package com.electronicapproval.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping("/home_list_view")
	public String homeListView(Model model) {
		
		model.addAttribute("viewName", "home/home_list");
		
		return "template/layout";
	}
	
}
