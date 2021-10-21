package com.electronicapproval.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.post.bo.PostBO;
import com.electronicapproval.post.model.Post;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private EmployeeBO employeeBO;
	
	@RequestMapping("/home_list_view")
	public String homeListView(Model model) {
		
		List<Post> post = postBO.getPostList();
		List<String> emoployeeName = new ArrayList<>();
		for (int i = 0; i < post.size(); i++) {
			int id = post.get(i).getEmployeeId();
			String name = employeeBO.getNameById(id);
			emoployeeName.add(name);
		}
		model.addAttribute("postNameList", emoployeeName);
		model.addAttribute("postList", post);
		
		model.addAttribute("viewName", "home/home_list");
		
		return "template/layout";
	}
	
}
