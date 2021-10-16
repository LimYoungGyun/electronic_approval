package com.electronicapproval.employee.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicapproval.employee.dao.EmployeeDAO;
import com.electronicapproval.employee.model.Employee;

@Service
public class EmployeeBO {
	
	@Autowired
	private EmployeeDAO employeeDAO;

	public Employee getEmployeeByEmailAndPassword(String email, String password) {
		return employeeDAO.selectEmployeeByEmailAndPassword(email, password);
	}
}
