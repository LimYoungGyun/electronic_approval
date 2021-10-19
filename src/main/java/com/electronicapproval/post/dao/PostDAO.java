package com.electronicapproval.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.electronicapproval.post.model.Post;

@Repository
public interface PostDAO {

	public List<Post> selectPostList();
	
	public Post selectPostByPostId(int postId);
	
	public int insertPost(Post post);
}
