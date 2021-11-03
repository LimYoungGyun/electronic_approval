package com.electronicapproval.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.electronicapproval.common.PageMaker;
import com.electronicapproval.common.Paging;
import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.file.bo.FileBO;
import com.electronicapproval.file.model.File;
import com.electronicapproval.post.bo.PostBO;
import com.electronicapproval.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private EmployeeBO employeeBO;
	
	@Autowired
	private FileBO fileBO;
	
	/**
	 * 공지사항 리스트 페이지로 이동.
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/post_list_view")
	public String postListView(
			@RequestParam(value="page", required=false) Integer page 
			, Model model) {

		int postCount = postBO.getPostListCount();
		
		Paging paging = new Paging();
		if (page == null) {
			page = 0;
		}
		paging.setPage(page);
		paging.setPageSize(10); // 한 페이지의 게시글 수
		paging.setTotalArticle(postCount); // 전체 게시글 수
		paging.setTotalPage(paging.getTotalArticle()); // 총 페이제 수
		paging.setStartRow(paging.getPage());
		paging.setEndRow(paging.getPage());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPaging(paging);
		
		List<Post> postListPage = postBO.getPostListPage(paging.getStartRow(), paging.getEndRow());
		model.addAttribute("paging", paging);
		model.addAttribute("pageMaker", pageMaker);
		
		List<String> emoployeeList = new ArrayList<>();
		for (int i = 0; i < postListPage.size(); i++) {
			int id = postListPage.get(i).getEmployeeId();
			Employee employee = employeeBO.getEmployeeById(id);
			emoployeeList.add(employee.getName());
		}
		model.addAttribute("emoployeeList", emoployeeList);
		model.addAttribute("postListPage", postListPage);
		
		model.addAttribute("viewName", "post/post_list");
		
		return "template/layout";
	}
	
	/**
	 * 공지사항 등록 페이지로 이동.
	 * @param model
	 * @return
	 */
	@RequestMapping("/post_insert_view")
	public String postInsertView(Model model) {
		
		model.addAttribute("viewName", "post/post_insert");
		
		return "template/layout";
	}
	
	/**
	 * 공지사항 상세 페이지로 이동.
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping("/post_detail_view")
	public String postInsertView(
			@RequestParam("postId") int postId
			, Model model) {
		
		Post post = postBO.getPostByPostId(postId);
		
		int id = post.getEmployeeId();
		Employee employee = employeeBO.getEmployeeById(id);
		String name = employee.getName();

		List<File> files = fileBO.getFileListById(postId);
		
		model.addAttribute("postFiles", files);
		model.addAttribute("postName", name);
		model.addAttribute("post", post);
		model.addAttribute("viewName", "post/post_detail");
		
		return "template/layout";
	}
	
	/**
	 * 공지사항 수정 페이지로 이동.
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping("/post_update_view")
	public String postUpdateView(
			@RequestParam("postId") int postId
			, Model model) {
		
		Post post = postBO.getPostByPostId(postId);
		
		int id = post.getEmployeeId();
		Employee employee = employeeBO.getEmployeeById(id);
		String name = employee.getName();
		
		List<File> files = fileBO.getFileListById(postId);
		
		model.addAttribute("postFiles", files);
		model.addAttribute("postName", name);
		model.addAttribute("post", post);
		model.addAttribute("viewName", "post/post_update");
		
		return "template/layout";
	}
}
