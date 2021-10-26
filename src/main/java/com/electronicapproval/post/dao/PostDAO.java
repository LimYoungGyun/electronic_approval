package com.electronicapproval.post.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.electronicapproval.post.model.Post;

@Repository
public interface PostDAO {

	public List<Post> selectPostList();
	
	public List<Post> selectPostListLimit5();
	
	public Post selectPostByPostId(int postId);
	
	public int insertPost(Post post);

	public int updatePost(Post post);
}
