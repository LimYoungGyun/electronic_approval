package com.electronicapproval.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.electronicapproval.common.PageMaker;
import com.electronicapproval.common.Paging;
import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.EmployeeInfoView;
import com.electronicapproval.group.bo.GroupBO;
import com.electronicapproval.group.model.Group;
import com.electronicapproval.official.bo.OfficialBO;
import com.electronicapproval.official.model.Official;
import com.electronicapproval.position.bo.PositionBO;
import com.electronicapproval.position.model.Position;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeBO employeeBO;
	
	@Autowired
	private GroupBO groupBO;
	
	@Autowired
	private PositionBO positionBO;
	
	@Autowired
	private OfficialBO officialBO;

	/**
	 * 직원관리 리스트 페이지로 이동.
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/employee_list_view")
	public String employeeListView(
			@RequestParam(value="page", required=false) Integer page 
			,Model model) {
		
		// total
		int employeeCount = employeeBO.getEmployeeListCount();
		
		Paging paging = new Paging();
		if (page == null) {
			page = 0;
		}
		paging.setPage(page);
		paging.setPageSize(10); // 한 페이지의 게시글 수
		paging.setTotalArticle(employeeCount); // 전체 게시글 수
		paging.setTotalPage(paging.getTotalArticle()); // 총 페이제 수
		paging.setStartRow(paging.getPage());
		paging.setEndRow(paging.getPage());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPaging(paging);
		
		List<EmployeeInfoView> employeeInfoViewList = employeeBO.employeeInfoViewList(paging.getStartRow(), paging.getEndRow());
		model.addAttribute("paging", paging);
		model.addAttribute("pageMaker", pageMaker);
		
		model.addAttribute("employeeInfoViewList", employeeInfoViewList);
		model.addAttribute("viewName", "employee/employee_list");
		
		return "template/layout";
	}
	
	/**
	 * 직원관리 등록 페이지로 이동.
	 * @param model
	 * @return
	 */
	@RequestMapping("/employee_insert_view")
	public String employeeInsertView(Model model) {
		
		List<Group> groupList = groupBO.getGroupList();
		List<Position> positionList = positionBO.getPositionList();
		List<Official> officialList = officialBO.getOfficialList();
		
		model.addAttribute("groupList", groupList);
		model.addAttribute("positionList", positionList);
		model.addAttribute("officialList", officialList);
		model.addAttribute("viewName", "employee/employee_insert");
		
		return "template/layout";
	}
	
	/**
	 * 직원관리 상세 페이지로 이동.
	 * @param employeeId
	 * @param model
	 * @return
	 */
	@RequestMapping("/employee_detail_view")
	public String employeeInsertView(
			@RequestParam("employeeId") int employeeId
			, Model model) {
		EmployeeInfoView employeeInfoView = employeeBO.getemployeeInfoView(employeeId);
	
		
		model.addAttribute("employeeInfoView", employeeInfoView);
		model.addAttribute("viewName", "employee/employee_detail");
		
		return "template/layout";
	}
	
	/**
	 * 직원관리 수정 페이지로 이동.
	 * @param employeeId
	 * @param model
	 * @return
	 */
	@RequestMapping("/employee_update_view")
	public String employeeUpdateView(
			@RequestParam("employeeId") int employeeId
			, Model model) {
		EmployeeInfoView employeeInfoView = employeeBO.getemployeeInfoView(employeeId);
		List<Group> groupList = groupBO.getGroupList();
		List<Position> positionList = positionBO.getPositionList();
		List<Official> officialList = officialBO.getOfficialList();
		
		model.addAttribute("groupList", groupList);
		model.addAttribute("positionList", positionList);
		model.addAttribute("officialList", officialList);
		model.addAttribute("employeeInfoView", employeeInfoView);
		model.addAttribute("viewName", "employee/employee_update");
		
		return "template/layout";
	}
}
