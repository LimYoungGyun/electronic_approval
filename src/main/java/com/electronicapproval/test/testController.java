package com.electronicapproval.test;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.electronicapproval.test.bo.TestBO;

@Controller
public class testController {
	
	@Autowired
	private TestBO testBO;

	@ResponseBody
	@RequestMapping("/test")
	public String test() {
		return "Hello World!!!";
	}
	
	@ResponseBody
	@RequestMapping("/test2")
	public List<Map<String, Object>> test2() {
		return testBO.getUserList();
	}
}
