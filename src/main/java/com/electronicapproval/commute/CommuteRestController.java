package com.electronicapproval.commute;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electronicapproval.commute.bo.CommuteBO;

@RestController
@RequestMapping("/commute")
public class CommuteRestController {
	
	@Autowired
	private CommuteBO commuteBO;
	
	/**
	 * 출근 등록 API.
	 * @param urlPath
	 * @param request
	 * @return
	 */
	@PostMapping("/commute_attendanceTime")
	public Map<String, Object> commuteAttendanceTime(
			@RequestParam("urlPath") String urlPath
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int employeeId = (int) session.getAttribute("employeeId");
		
		int cnt = commuteBO.addAttendanceTime(employeeId);
		
		Map<String, Object> result = new HashMap<>();
		
		if (cnt >= 1) {
			// session에 정보 담기.
			session.setAttribute("commuteStatus", true);
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
	 * 퇴근 등록 API. (출근때 등록된 컬럼을 수정)
	 * @param urlPath
	 * @param request
	 * @return
	 */
	@PutMapping("/commute_quittingTime")
	public Map<String, Object> commuteQuittingTime(
			@RequestParam("urlPath") String urlPath
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int employeeId = (int) session.getAttribute("employeeId");
		
		int cnt = commuteBO.updateQuittingTime(employeeId);
		
		Map<String, Object> result = new HashMap<>();
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
}
