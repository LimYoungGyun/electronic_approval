package com.electronicapproval.group;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.electronicapproval.group.bo.GroupBO;
import com.electronicapproval.group.model.Group;

@Controller
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	private GroupBO groupBO;
	
	/**
	 * 그룹 관리 리스트 화면으로 이동.
	 * @param model
	 * @return
	 */
	@RequestMapping("/group_list_view")
	public String postListView(Model model) {
		
		List<Group> group = groupBO.getGroupList();

		model.addAttribute("groupList", group);
		model.addAttribute("viewName", "group/group_list");
		
		return "template/layout";
	}
	
	/**
	 * 그룹 관리 등록 화면으로 이동.
	 * @param model
	 * @return
	 */
	@RequestMapping("/group_insert_view")
	public String groupInsertView(Model model) {
		
		List<Group> group = groupBO.getGroupList();
		
		model.addAttribute("groupList", group);
		model.addAttribute("viewName", "group/group_insert");
		
		return "template/layout";
	}
	
	/**
	 * 그룹 관리 상세 화면으로 이동.
	 * @param groupId
	 * @param model
	 * @return
	 */
	@RequestMapping("/group_detail_view")
	public String groupDetailView(
			@RequestParam("groupId") int groupId
			, Model model) {
		
		Group group = groupBO.getGroupById(groupId);
		
		model.addAttribute("group", group);
		model.addAttribute("viewName", "group/group_detail");
		
		return "template/layout";
	}
	
	/**
	 * 그룹 관리 수정 화면으로 이동.
	 * @param groupId
	 * @param model
	 * @return
	 */
	@RequestMapping("/group_update_view")
	public String groupUpdateView(
			@RequestParam("groupId") int groupId
			, Model model) {
		
		Group group = groupBO.getGroupById(groupId);
		List<Group> groupList = groupBO.getGroupList();
		
		model.addAttribute("group", group);
		model.addAttribute("groupList", groupList);
		model.addAttribute("viewName", "group/group_update");
		
		return "template/layout";
	}
}
