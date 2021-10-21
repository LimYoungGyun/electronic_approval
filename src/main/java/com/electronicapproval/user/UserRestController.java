package com.electronicapproval.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private EmployeeBO employeeBO;

	@RequestMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("email") String email
			, @RequestParam("password") String password
			, HttpServletRequest request) {
		
		Employee employee = employeeBO.getEmployeeByEmailAndPassword(email, password);
		
		Map<String, Object> result = new HashMap<>();
		if (employee != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("employeeId", employee.getId());
			session.setAttribute("authorityPost", employee.getAuthorityPost());
			session.setAttribute("authorityGroup", employee.getAuthorityGroup());
			
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
}
