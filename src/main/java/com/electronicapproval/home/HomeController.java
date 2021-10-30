package com.electronicapproval.home;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.electronicapproval.commute.bo.CommuteBO;
import com.electronicapproval.commute.model.CommuteInfoView;
import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.post.bo.PostBO;
import com.electronicapproval.post.model.Post;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private EmployeeBO employeeBO;
	
	@Autowired
	private CommuteBO commuteBO;
	
	@RequestMapping("/home_list_view")
	public String homeListView(Model model) {
		
		// 공지사항
		List<Post> post = postBO.getPostListLimit5();
		List<String> emoployeeList = new ArrayList<>();
		for (int i = 0; i < post.size(); i++) {
			int id = post.get(i).getEmployeeId();
			Employee employee = employeeBO.getEmployeeById(id);
			emoployeeList.add(employee.getName());
		}
		
		// 출퇴근
		List<CommuteInfoView> commuteInfoViewList = new ArrayList<CommuteInfoView>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 05:00:00");
		
		LocalDateTime today = LocalDateTime.now();
		String startDate = today.format(formatter);
		model.addAttribute("startDate", startDate);
		
		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
		String endDate = tomorrow.format(formatter);
		model.addAttribute("endDate", endDate);
		
		commuteInfoViewList = commuteBO.getCommuteList(startDate, endDate);
		
		
		
		model.addAttribute("commuteInfoViewList", commuteInfoViewList);
		model.addAttribute("emoployeeList", emoployeeList);
		model.addAttribute("postList", post);
		
		model.addAttribute("viewName", "home/home_list");
		
		return "template/layout";
	}
	
}
