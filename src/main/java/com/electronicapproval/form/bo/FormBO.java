package com.electronicapproval.form.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.form.dao.FormDAO;
import com.electronicapproval.form.model.Form;
import com.electronicapproval.form.model.FormInfoView;
import com.electronicapproval.position.bo.PositionBO;

@Service
public class FormBO {
	
	@Autowired
	private FormDAO formDAO;
	
	@Autowired
	private EmployeeBO employeeBO;
	
	@Autowired
	private PositionBO positionBO;

	public List<FormInfoView> getFormInfoViewList(int employeeId, int startRow, int endRow) {
		
		List<FormInfoView> formInfoViewList = new ArrayList<>();
		
		List<Form> formList = new ArrayList<>();
		
		Employee employee = employeeBO.getEmployeeById(employeeId);
		Integer officialId = employee.getOfficialId();
		
		formList = formDAO.selectFormListByEmployeeId(employeeId, officialId, startRow, endRow);
		
		for (Form form : formList) {
			FormInfoView formInfoView = new FormInfoView();
			
			// 기안서
			formInfoView.setForm(form);
			
			// 직원 정보
			formInfoView.setEmployee(employeeBO.getEmployeeById(form.getEmployeeId()));
			
			// 직급
			formInfoView.setPosition(positionBO.getPositionById(form.getPositionId()));
			
			formInfoViewList.add(formInfoView);
		}
		
		return formInfoViewList;
	}
	
	public int getFromListCount(int employeeId) {
		
		Employee employee = employeeBO.getEmployeeById(employeeId);
		Integer officialId = employee.getOfficialId();
		
		return formDAO.selectFromListCount(employeeId, officialId);
	}
	
	public Form getFormByFormId(int id) {
		return formDAO.selectFormByFormId(id);
	}
	
	public int addFrom(Form form) {
		
		Employee employee = employeeBO.getEmployeeById(form.getEmployeeId());
		form.setPositionId(employee.getPositionId());
		
		return formDAO.insertFrom(form);
	}
	
	public int updateFormDisapproval(int formId, String reContent) {
		
		String status = "반려";
		
		return formDAO.updateFormDisapproval(formId, status, reContent);
	}
	
	@Transactional
	public int updateFormApproval(int formId, int emplyoeeId, int count) {
		
		// 직원 정보를 구한다.
		Employee employee = employeeBO.getEmployeeById(emplyoeeId);
		// 휴가 개수를 차감
		employee.setUseAnnualLeave(employee.getUseAnnualLeave() + count);
		employee.setRemainAnnualLeave(employee.getRemainAnnualLeave() - count);
		
		employeeBO.updateEmployeeByNoPassword(employee, null, true);
		
		String status = "승인";
		
		return formDAO.updateFormApproval(formId, status);
	}
	
	public int updateForm(Form form) {
		
		form.setStatus("결재 요청");
		form.setReContent(null);
		
		return formDAO.updateForm(form);
	}
}
