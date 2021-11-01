package com.electronicapproval.form.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<FormInfoView> getFormInfoViewList(int employeeId) {
		
		List<FormInfoView> formInfoViewList = new ArrayList<>();
		
		List<Form> formList = new ArrayList<>();
		
		Employee employee = employeeBO.getEmployeeById(employeeId);
		Integer officialId = employee.getOfficialId();
//		if (officialId != null) {
//			// 직책이 있으면
//			formList = formDAO.selectFormListByEmployeeId(employeeId, officialId);
//		} else {
//			// 직책이 없으면
//			formList = formDAO.selectFormListByEmployeeId(employeeId, officialId);
//		}
		formList = formDAO.selectFormListByEmployeeId(employeeId, officialId);
		
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
}
