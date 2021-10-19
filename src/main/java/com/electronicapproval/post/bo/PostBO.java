package com.electronicapproval.post.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.electronicapproval.common.FileManagerService;
import com.electronicapproval.file.bo.FileBO;
import com.electronicapproval.post.dao.PostDAO;
import com.electronicapproval.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private FileBO fileBO;
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public Post getPostByPostId(int postId) {
		return postDAO.selectPostByPostId(postId);
	}
	
	@Transactional
	public int addPost(Post post, List<MultipartFile> files) {
		int fileCnt = 0;
		int postCnt = 0;
		
		if (!CollectionUtils.isEmpty(files)) {
			postCnt = postDAO.insertPost(post);
			
			if (postCnt >= 1) {
				int postId = post.getId();
				
				String directoryName = null;
				String filePath = "";
				for (MultipartFile file : files) {
					try {
						filePath = fileManagerService.saveFile(postId, file, directoryName);
						directoryName = (filePath.substring(1, filePath.length()).split("/")[1]) + "/";
					} catch (IOException e) {
						filePath = null;
					}
					// file테이블에 넣기
					fileCnt = fileBO.addFiles(postId, filePath);
					
					if (fileCnt == 0) {
						postCnt = 0;
						break;
					}
				}
			}
		} else {
			postCnt = postDAO.insertPost(post);
		}
		
		if (postCnt >= 1) {
			return postCnt;
		}
		return postCnt;
	}
}
