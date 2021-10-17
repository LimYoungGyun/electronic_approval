package com.electronicapproval.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.post.bo.PostBO;
import com.electronicapproval.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private EmployeeBO employeeBO;

	@RequestMapping("/post_list_view")
	public String postListView(Model model) {
		
		List<Post> post = postBO.getPostList();
		List<String> emoployeeName = new ArrayList<>();
		for (int i = 0; i < post.size(); i++) {
			int id = post.get(i).getEmployeeId();
			String name = employeeBO.getNameById(id);
			emoployeeName.add(name);
		}
		model.addAttribute("postNameList", emoployeeName);
		model.addAttribute("postList", post);
		model.addAttribute("viewName", "post/post_list");
		
		return "template/layout";
	}
	
	@RequestMapping("/post_insert_view")
	public String postInsertView(Model model) {
		
		model.addAttribute("viewName", "post/post_insert");
		
		return "template/layout";
	}
	
	@RequestMapping("post_detail_view")
	public String postInsertView(
			@RequestParam("postId") int postId
			, Model model) {
		
		Post post = postBO.getPostByPostId(postId);
		
		model.addAttribute("post", post);
		model.addAttribute("viewName", "post/post_detail");
		
		return "template/layout";
	}
}
