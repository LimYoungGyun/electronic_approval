package com.electronicapproval.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.electronicapproval.post.model.Post;

@Repository
public interface PostDAO {
	
	// page
	public List<Post> selectPostListPage(
			@Param("startRow") int startRow
			, @Param("endRow") int endRow);

	public int selectPostListCount();
	
	public List<Post> selectPostListLimit5();
	
	public Post selectPostByPostId(int postId);
	
	public int insertPost(Post post);

	public int updatePost(Post post);
	
	public int deletePostById(int id);
}
