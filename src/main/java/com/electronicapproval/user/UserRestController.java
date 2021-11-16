package com.electronicapproval.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electronicapproval.common.EncryptUtils;
import com.electronicapproval.common.Sha512;
import com.electronicapproval.commute.bo.CommuteBO;
import com.electronicapproval.commute.model.Commute;
import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.group.bo.GroupBO;
import com.electronicapproval.group.model.Group;
import com.electronicapproval.position.bo.PositionBO;
import com.electronicapproval.position.model.Position;

@RestController
public class UserRestController {
	
	@Autowired
	private EmployeeBO employeeBO;
	
	@Autowired
	private GroupBO groupBO;
	
	@Autowired
	private PositionBO positionBO;
	
	@Autowired
	private CommuteBO commuteBO;
	
	/**
	 * 로그인 API.
	 * @param email
	 * @param password
	 * @param request
	 * @return
	 */
	@PostMapping("/user/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("email") String email
			, @RequestParam("password") String password
			, HttpServletRequest request) {
		
//		String encryptPassword = EncryptUtils.md5(password);
		String encryptPassword = Sha512.getSHA512(password);
		
		Employee employee = employeeBO.getEmployeeByEmailAndPassword(email, encryptPassword);
		
		Map<String, Object> result = new HashMap<>();
		if (employee != null) {
			
			HttpSession session = request.getSession();
			int employeeId = employee.getId();
			
			// 출퇴근 현황을 확인하기 위한 작업
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 05:00:00");
			LocalDateTime today = LocalDateTime.now();
			String startDate = today.format(formatter);
			LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
			String endDate = tomorrow.format(formatter);
			Commute commute = commuteBO.getCommuteByIdAndDates(employeeId, startDate, endDate);
			if (commute != null) {
				session.setAttribute("commuteStatus", true);
			} else {
				session.setAttribute("commuteStatus", false);
			}
			
			if (groupBO.getGroupById(employee.getGroupId()) == null) {
				String employeeGroup = "";
				session.setAttribute("employeeGroup", employeeGroup); // gnb
			} else {
				Group employeeGroup = groupBO.getGroupById(employee.getGroupId());
				session.setAttribute("employeeGroup", employeeGroup.getGroupName()); // gnb
			}
			
			Position employeePosition = positionBO.getPositionById(employee.getPositionId());
			
			session.setAttribute("employeeId", employee.getId());
			session.setAttribute("useLogin", employee.isUseLogin());
			session.setAttribute("employeeEmail", employee.getEmail());
			session.setAttribute("profilePath", employee.getProfilePath());
			session.setAttribute("employeeName", employee.getName());
			session.setAttribute("employeeGroupId", employee.getGroupId());
			session.setAttribute("employeePosition", employeePosition.getName());
			session.setAttribute("authorityPost", employee.getAuthorityPost());
			session.setAttribute("authorityGroup", employee.getAuthorityGroup());
			session.setAttribute("authorityEmployee", employee.getAuthorityEmployee());
			session.setAttribute("authorityCommute", employee.getAuthorityCommute());
			session.setAttribute("authorityForm", employee.getAuthorityForm());
			
			// 처음 로그인을 진행.
			if (employee.isUseLogin() == false) {
				result.put("result", "movePassword");
			} else {
				result.put("result", "success");
			}
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
	 * 최조 로그인시에 비밀번호 변경 API.
	 * @param password
	 * @param request
	 * @return
	 */
	@PutMapping("/password/password_change")
	public Map<String, Object> passwordChange(
			@RequestParam("password") String password
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Map<String, Object> result = new HashMap<>();
		try {
			int employeeId = (int) session.getAttribute("employeeId");
			
			Employee employee = employeeBO.getEmployeeById(employeeId);
			
//			String encryptPassword = EncryptUtils.md5(password);
			String encryptPassword = Sha512.getSHA512(password);
			employee.setPassword(encryptPassword);
			employee.setUseLogin(true);
			
			
			int cnt = employeeBO.updateEmployeeByPassword(employee, null, true);
			session.invalidate();
			
			if (cnt >= 1) {
				result.put("result", "success");
			} else {
				result.put("result", "fail");
			}
		} catch (Exception e) {
			session.invalidate();
		}
		
		return result;
	}
}
