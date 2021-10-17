package com.electronicapproval.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicapproval.post.dao.PostDAO;
import com.electronicapproval.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public Post getPostByPostId(int postId) {
		return postDAO.selectPostByPostId(postId);
	}
	
	public int addPost(String title, String content, int employeeId) {
		return postDAO.insertPost(title, content, employeeId);
	}
}
