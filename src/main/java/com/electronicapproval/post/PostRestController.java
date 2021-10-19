package com.electronicapproval.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.electronicapproval.post.bo.PostBO;
import com.electronicapproval.post.model.Post;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;

	/**
	 * 공지사항 등록 API.
	 * @param post
	 * @param files
	 * @param request
	 * @return
	 */
	@PostMapping("/insert")
	public Map<String, Object> postInsert(
			@ModelAttribute Post post 
			, @RequestParam(value = "filesArr", required = false) List<MultipartFile> files
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int employeeId = (int) session.getAttribute("employeeId");
		
		Map<String, Object> result = new HashMap<>();
		post.setEmployeeId(employeeId);
		
		int cnt = postBO.addPost(post, files);
		
		if (cnt >= 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
}
