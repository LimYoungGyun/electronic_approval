package com.electronicapproval.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electronicapproval.group.bo.GroupBO;
import com.electronicapproval.group.model.Group;

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
	
	/**
	 * 그룹 관리 수정 API.
	 * @param groupId
	 * @param groupName
	 * @param topLevelId
	 * @param content
	 * @return
	 */
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
	
	/**
	 * 그룹 관리 삭제 API.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("id") int id) {
		
		Map<String, Object> result = new HashMap<>();
		
		List<Group> groupList = groupBO.getGroupListById(id);
		if (CollectionUtils.isEmpty(groupList)) {
			int cnt = groupBO.deleteGroup(id);
			
			if (cnt >= 1) {
				result.put("result", "success");
			} else {
				result.put("result", "fail");
			}
		} else {
			result.put("result", "group");
		}
		
		return result;
	}
}
