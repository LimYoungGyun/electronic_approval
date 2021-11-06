package com.electronicapproval.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.electronicapproval.common.PageMaker;
import com.electronicapproval.common.Paging;
import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.form.bo.FormBO;
import com.electronicapproval.form.model.Form;
import com.electronicapproval.form.model.FormInfoView;

@Controller
@RequestMapping("/form")
public class FormController {
	
	@Autowired
	private FormBO formBO;
	
	@Autowired
	private EmployeeBO employeeBO;
	
	/**
	 * 기안서 관리 리스트 페이지로 이동.
	 * @param page
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/form_list_view")
	public String formListView(
			@RequestParam(value="page", required=false) Integer page
			, HttpServletRequest request
			, Model model) {
		
		HttpSession session = request.getSession();
		int employeeId = (int) session.getAttribute("employeeId");
		
		// 게시물 개수 가져오기
		int formListCount = formBO.getFromListCount(employeeId);
		
		Paging paging = new Paging();
		if (page == null) {
			page = 0;
		}
		paging.setPage(page);
		paging.setPageSize(10); // 한 페이지의 게시글 수
		paging.setTotalArticle(formListCount); // 전체 게시글 수
		paging.setTotalPage(paging.getTotalArticle()); // 총 페이제 수
		paging.setStartRow(paging.getPage());
		paging.setEndRow(paging.getPage());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPaging(paging);
		
		model.addAttribute("paging", paging);
		model.addAttribute("pageMaker", pageMaker);
		
		List<FormInfoView> formListPage = formBO.getFormInfoViewList(employeeId, paging.getStartRow(), paging.getEndRow());
		
		model.addAttribute("formInfoViewList", formListPage);
		model.addAttribute("viewName", "form/form_list");
		
		return "template/layout";
	}
	
	/**
	 * 기안서 관리 등록 페이지로 이동.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/form_insert_view")
	public String formInsertView(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		int employeeId = (int) session.getAttribute("employeeId");
		
		Employee employee = employeeBO.getEmployeeById(employeeId);
		
		// 남은 연차수
		int remainAnnualLeave = employee.getRemainAnnualLeave();
		
		// 발신자명
		int employeeGroupId = (int) session.getAttribute("employeeGroupId"); // session에 있는 그룹 번호 가져오기
		
		// 발신자 조회
		// 나와 같은 그룹 번호를 가지고 있으면서 기안서 권한이 있는사람
		List<Employee> employeeList = employeeBO.getEmployeeList();
		List<Employee> annualIncome = new ArrayList<>();
		for (Employee employees : employeeList) {
			if (employees.getGroupId() == employeeGroupId && "A".equals(employees.getAuthorityForm())) {
				annualIncome.add(employees);
			}
		}
		
		model.addAttribute("annualIncome", annualIncome);
		model.addAttribute("remainAnnualLeave", remainAnnualLeave);
		model.addAttribute("viewName", "form/form_insert");
		
		return "template/layout";
	}
	
	/**
	 * 기안서 관리 상세 페이지로 이동.
	 * @param formId
	 * @param model
	 * @return
	 */
	@RequestMapping("/form_detail_view")
	public String formDetailView(
			@RequestParam("formId") int formId
			, Model model) {
		
		Form form = formBO.getFormByFormId(formId);
		String userName = employeeBO.getEmployeeById(form.getEmployeeId()).getName();
		String sendUserName = employeeBO.getEmployeeById(form.getSendTo()).getName();
		
		model.addAttribute("form", form);
		model.addAttribute("userName", userName);
		model.addAttribute("sendUserName", sendUserName);
		model.addAttribute("viewName", "form/form_detail");
		
		return "template/layout";
	}
	
	/**
	 * 기안서 관리 수정 페이지로 이동.
	 * @param formId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/form_update_view")
	public String formUpdateView(
			@RequestParam("formId") int formId
			, HttpServletRequest request
			, Model model) {
		
		// 기안서 데이터
		Form form = formBO.getFormByFormId(formId);
		model.addAttribute("form", form);
		
		// 직원 정보 가져오기
		HttpSession session = request.getSession();
		int employeeId = (int) session.getAttribute("employeeId");
		Employee employee = employeeBO.getEmployeeById(employeeId);
		
		// 위의 직원 정보로 남은 연차 가져오기.
		int remainAnnualLeave = employee.getRemainAnnualLeave();
		model.addAttribute("remainAnnualLeave", remainAnnualLeave);
		
		// 발신자 조회
		// 나와 같은 그룹 번호를 가지고 있으면서 기안서 권한이 있는사람
		// 발신자명
		int employeeGroupId = (int) session.getAttribute("employeeGroupId"); // session에 있는 그룹 번호 가져오기
		List<Employee> employeeList = employeeBO.getEmployeeList();
		List<Employee> annualIncome = new ArrayList<>();
		for (Employee employees : employeeList) {
			if (employees.getGroupId() == employeeGroupId && "A".equals(employees.getAuthorityForm())) {
				annualIncome.add(employees);
			}
		}
		model.addAttribute("annualIncome", annualIncome);
		model.addAttribute("viewName", "form/form_update");
		
		return "template/layout";
	}
}
