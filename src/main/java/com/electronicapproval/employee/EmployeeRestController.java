package com.electronicapproval.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.electronicapproval.common.EncryptUtils;
import com.electronicapproval.common.FileManagerService;
import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.employee.model.EmployeeInfoView;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeBO employeeBO;

	@PostMapping("/employee_insert")
	public Map<String, Object> employeeInsert(
			@ModelAttribute Employee employee
			, @RequestParam("file") MultipartFile file
			, HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<>();
		
		// 이메일 중복확인.
		String employeeEmail = employee.getEmail();
		List<Employee> employeeList = employeeBO.getEmployeeList();
		for(Employee checkEmployee : employeeList) {
			if (employeeEmail.equals(checkEmployee.getEmail())) {
				result.put("result", "Emailoverlap");
				return result;
			}
		}
		
		String encryptPassword = EncryptUtils.md5(employee.getPassword());
		employee.setPassword(encryptPassword);
		
		int cnt = employeeBO.addEmployeeInsert(employee, file);
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@PutMapping("/employee_update")
	public Map<String, Object> employeeUpdate(
			@ModelAttribute Employee employee 
			, @RequestParam(value = "file", required = false) MultipartFile file
			, @RequestParam(value = "filePath", required = false) Boolean filePath) {
		
		Map<String, Object> result = new HashMap<>();
		
		// 이메일 중복확인.
		String employeeEmail = employee.getEmail();
		List<Employee> employeeList = employeeBO.getEmployeeList();
		for(Employee checkEmployee : employeeList) {
			if (checkEmployee.getId() == employee.getId()) {
				continue;
			}
			if (employeeEmail.equals(checkEmployee.getEmail())) {
				result.put("result", "Emailoverlap");
				return result;
			}
		}
		
		int cnt = 0;
		// 비밀번호가 ""일때
		if ("".equals(employee.getPassword())) {
			cnt = employeeBO.updateEmployeeByNoPassword(employee, file, filePath);
		} 
		// 비밀번호가 ""가 아닐때
		else {
			employee.setPassword(EncryptUtils.md5(employee.getPassword()));
			cnt = employeeBO.updateEmployeeByPassword(employee, file, filePath);
		}
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
}
