package com.electronicapproval.group;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.electronicapproval.common.PageMaker;
import com.electronicapproval.common.Paging;
import com.electronicapproval.group.bo.GroupBO;
import com.electronicapproval.group.model.Group;

@Controller
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	private GroupBO groupBO;
	
	/**
	 * 그룹 관리 리스트 화면으로 이동.
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/group_list_view")
	public String groupListView(
			@RequestParam(value="page", required=false) Integer page 
			, Model model) {
		
		int groupCount = groupBO.getGroupListCount();
		
		Paging paging = new Paging();
		if (page == null) {
			page = 0;
		}
		paging.setPage(page);
		paging.setPageSize(10); // 한 페이지의 게시글 수
		paging.setTotalArticle(groupCount); // 전체 게시글 수
		paging.setTotalPage(paging.getTotalArticle()); // 총 페이제 수
		paging.setStartRow(paging.getPage());
		paging.setEndRow(paging.getPage());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPaging(paging);
		
		List<Group> groupListPage = groupBO.getGroupListPage(paging.getStartRow(), paging.getEndRow());
		model.addAttribute("paging", paging);
		model.addAttribute("pageMaker", pageMaker);

		model.addAttribute("groupListPage", groupListPage);
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
