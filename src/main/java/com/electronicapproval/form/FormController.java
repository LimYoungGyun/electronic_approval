package com.electronicapproval.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.form.bo.FormBO;
import com.electronicapproval.form.model.FormInfoView;

@Controller
@RequestMapping("/form")
public class FormController {
	
	@Autowired
	private FormBO formBO;
	
	@Autowired
	private EmployeeBO employeeBO;

	@RequestMapping("/form_list_view")
	public String formListView(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		int employeeId = (int) session.getAttribute("employeeId");
		
		List<FormInfoView> formInfoViewList = formBO.getFormInfoViewList(employeeId);
		
		model.addAttribute("formInfoViewList", formInfoViewList);
		model.addAttribute("viewName", "form/form_list");
		
		return "template/layout";
	}
	
	@RequestMapping("/form_insert_view")
	public String formInsertView(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		int employeeId = (int) session.getAttribute("employeeId");
		
		Employee employee = employeeBO.getEmployeeById(employeeId);
		int remainAnnualLeave = employee.getRemainAnnualLeave();
		
		model.addAttribute("remainAnnualLeave", remainAnnualLeave);
		model.addAttribute("viewName", "form/form_insert");
		
		return "template/layout";
	}
}
