package com.electronicapproval.employee.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicapproval.employee.dao.EmployeeDAO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.employee.model.EmployeeInfoView;
import com.electronicapproval.group.bo.GroupBO;
import com.electronicapproval.group.model.Group;
import com.electronicapproval.official.bo.OfficialBO;
import com.electronicapproval.official.model.Official;
import com.electronicapproval.position.bo.PositionBO;
import com.electronicapproval.position.model.Position;

@Service
public class EmployeeBO {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private GroupBO groupBO;
	
	@Autowired
	private PositionBO positionBO;
	
	@Autowired
	private OfficialBO officialBO;
	
	public List<Employee> getEmployeeList() {
		return employeeDAO.selectEmployeeList();
	}
	
	public List<EmployeeInfoView> employeeInfoViewList() {
		
		// 전체 내용 저장할 List
		List<EmployeeInfoView> employeeInfoViewList = new ArrayList<>();
		
		// 한 행이 출력할 정보 저장용 List
		List<Employee> employeeList = getEmployeeList();
		for (Employee employee : employeeList) {
			
			// 전체 내용을 저장할 객체.
			EmployeeInfoView employeeInfoView = new EmployeeInfoView();
			employeeInfoView.setEmployee(employee);;
			
			// 부서(그룹)
			Group group = groupBO.getGroupById(employee.getGroupId());
			employeeInfoView.setGroup(group);
			
			// 직급
			Position position = positionBO.getPositionById(employee.getPositionId());
			employeeInfoView.setPosition(position);
			
			// 직책
			Official official = officialBO.getOfficialById(employee.getOfficialId());
			employeeInfoView.setOfficial(official);
			
			employeeInfoViewList.add(employeeInfoView);
		}
		
		return employeeInfoViewList;
	}

	public Employee getEmployeeByEmailAndPassword(String email, String password) {
		return employeeDAO.selectEmployeeByEmailAndPassword(email, password);
	}
	
	public String getNameById(int id) {
		return employeeDAO.selectNameById(id);
	}
}