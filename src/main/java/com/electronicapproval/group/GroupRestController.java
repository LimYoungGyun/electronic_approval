package com.electronicapproval.group;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electronicapproval.group.bo.GroupBO;

@RestController
@RequestMapping("/group")
public class GroupRestController {
	
	@Autowired
	private GroupBO groupBO;

	/**
	 * 그룹 관리 등록 API.
	 * @param groupName
	 * @param id
	 * @param content
	 * @return
	 */
	@PostMapping("/insert")
	public Map<String, Object> insert(
			@RequestParam("groupName") String groupName
			, @RequestParam("topLevelId") int topLevelId
			, @RequestParam("content") String content) {

		Map<String, Object> result = new HashMap<>();

		// insert
		int cnt = groupBO.addGroup(groupName, topLevelId, content);
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		return result;
	}
	
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("groupId") int groupId
			, @RequestParam("groupName") String groupName
			, @RequestParam("topLevelId") int topLevelId
			, @RequestParam("content") String content) {

		Map<String, Object> result = new HashMap<>();
		
		// update
		int cnt = groupBO.updateGroup(groupId, groupName, topLevelId, content);
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		
		return result;
	}
	
	
}