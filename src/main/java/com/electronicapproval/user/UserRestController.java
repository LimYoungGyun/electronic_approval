package com.electronicapproval.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electronicapproval.common.EncryptUtils;
import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.group.bo.GroupBO;
import com.electronicapproval.group.model.Group;
import com.electronicapproval.position.bo.PositionBO;
import com.electronicapproval.position.model.Position;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private EmployeeBO employeeBO;
	
	@Autowired
	private GroupBO groupBO;
	
	@Autowired
	private PositionBO positionBO;

	@RequestMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("email") String email
			, @RequestParam("password") String password
			, HttpServletRequest request) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		Employee employee = employeeBO.getEmployeeByEmailAndPassword(email, encryptPassword);
		
		Map<String, Object> result = new HashMap<>();
		if (employee != null) {
			HttpSession session = request.getSession();
			
			if (groupBO.getGroupById(employee.getGroupId()) == null) {
				String employeeGroup = "";
				session.setAttribute("employeeGroup", employeeGroup);
			} else {
				Group employeeGroup = groupBO.getGroupById(employee.getGroupId());
				session.setAttribute("employeeGroup", employeeGroup.getGroupName());
			}
			
			Position employeePosition = positionBO.getPositionById(employee.getPositionId());
			
			session.setAttribute("employeeId", employee.getId());
			session.setAttribute("employeeEmail", employee.getEmail());
			session.setAttribute("profilePath", employee.getProfilePath());
			session.setAttribute("employeeName", employee.getName());
			session.setAttribute("employeePosition", employeePosition.getName());
			session.setAttribute("authorityPost", employee.getAuthorityPost());
			session.setAttribute("authorityGroup", employee.getAuthorityGroup());
			session.setAttribute("authorityEmployee", employee.getAuthorityEmployee());
			
			
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
}
