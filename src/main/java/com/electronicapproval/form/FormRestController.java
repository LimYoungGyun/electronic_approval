package com.electronicapproval.form;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electronicapproval.form.bo.FormBO;
import com.electronicapproval.form.model.Form;

@RestController
@RequestMapping("/form")
public class FormRestController {
	
	@Autowired
	private FormBO formBO;
	
	/**
	 * 기안서 관리 등록 API.
	 * @param form
	 * @return
	 */
	@PostMapping("/insert")
	public Map<String, Object> formInsert(
			@ModelAttribute Form form) {
		
		Map<String, Object> result = new HashMap<>();
//		form.setReContent(null);
		form.setStatus("결재 요청");
		
		int cnt = formBO.addFrom(form);
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
	 * 기안서 관리 반려처리 API.
	 * @param formId
	 * @param reContent
	 * @return
	 */
	@PutMapping("/disapproval")
	public Map<String, Object> disapproval(
			@RequestParam("formId") int formId
			, @RequestParam("reContent") String reContent) {
		
		Map<String, Object> result = new HashMap<>();
		
		int cnt = formBO.updateFormDisapproval(formId, reContent);
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
	 * 기안서 관리 승인처리 API.
	 * @param id
	 * @param emplyoeeId
	 * @param count
	 * @return
	 */
	@PutMapping("/approval")
	public Map<String, Object> Approval(
			@RequestParam("id") int id
			, @RequestParam("emplyoeeId") int emplyoeeId
			, @RequestParam("count") int count) {
		
		Map<String, Object> result = new HashMap<>();
		
		int cnt = formBO.updateFormApproval(id, emplyoeeId, count);
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
	 * 기안서 관리 수정 API.
	 * @param form
	 * @return
	 */
	@PutMapping("/update")
	public Map<String, Object> formUpdate(@ModelAttribute Form form) {
		Map<String, Object> result = new HashMap<>();
		
		int cnt = formBO.updateForm(form);
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
}
