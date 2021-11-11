package com.electronicapproval.home;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.electronicapproval.commute.bo.CommuteBO;
import com.electronicapproval.commute.model.CommuteInfoView;
import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.form.bo.FormBO;
import com.electronicapproval.form.model.FormInfoView;
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
	private FormBO formBO;
	
	@Autowired
	private CommuteBO commuteBO;
	
	/**
	 * 홈 화면으로 페이지 이동.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/home_list_view")
	public String homeListView(HttpServletRequest request, Model model) {
		
		// 공지사항
		List<Post> post = postBO.getPostListLimit5();
		List<String> emoployeeList = new ArrayList<>();
		for (int i = 0; i < post.size(); i++) {
			int id = post.get(i).getEmployeeId();
			Employee employee = employeeBO.getEmployeeById(id);
			emoployeeList.add(employee.getName());
		}
		
		// 휴가자 목록
		HttpSession session = request.getSession();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime today = LocalDateTime.now();
		String nowDate = today.format(format);
		
		int groupId = (int) session.getAttribute("employeeGroupId");
		
		List<FormInfoView> formList = formBO.getFormListByGroupIdAndNowDate(groupId, nowDate);
		model.addAttribute("formList", formList);
		
		// 출퇴근
		List<CommuteInfoView> commuteInfoViewList = new ArrayList<CommuteInfoView>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 05:00:00");
		
		today = LocalDateTime.now();
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
