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
import com.electronicapproval.file.model.File;
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
	
	public List<Post> getPostListLimit5() {
		return postDAO.selectPostListLimit5();
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
	
	@Transactional
	public int updatePost(Post post, List<MultipartFile> files, Boolean filePath) {
		int postCnt = 0;
		if (!CollectionUtils.isEmpty(files)) {
			
			postCnt = postDAO.updatePost(post);
			List<File> fileList = fileBO.getFileListById(post.getId());
			
			// 파일첨부내용이 있을떄
			int fileCnt = 0;
			String directoryName = null;
			String path = "";
			if (!CollectionUtils.isEmpty(fileList)) {
				// 파일 삭제
				try {
					path = fileList.get(0).getFilePath();
					for (File file : fileList) {
						fileManagerService.deleteFile(file.getFilePath());
					}
					fileManagerService.deleteFolder(path);
					// DB 파일 삭제
					fileBO.deleteFileById(post.getId());
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 파일 등록
				for (MultipartFile file : files) {
					try {
						path = fileManagerService.saveFile(post.getId(), file, directoryName);
						directoryName = (path.substring(1, path.length()).split("/")[1]) + "/";
					} catch (IOException e) {
						path = null;
					}
					// file테이블에 넣기
					fileCnt = fileBO.addFiles(post.getId(), path);
					
					if (fileCnt < 1) {
						postCnt = 0;
						break;
					}
				}
			} else {
				// 파일 등록
				for (MultipartFile file : files) {
					try {
						path = fileManagerService.saveFile(post.getId(), file, directoryName);
						directoryName = (path.substring(1, path.length()).split("/")[1]) + "/";
					} catch (IOException e) {
						path = null;
					}
					// file테이블에 넣기
					fileCnt = fileBO.addFiles(post.getId(), path);
					
					if (fileCnt < 1) {
						postCnt = 0;
						break;
					}
				}
			}
		} else if (CollectionUtils.isEmpty(files) && filePath == false) {
			postCnt = postDAO.updatePost(post);
			List<File> fileList = fileBO.getFileListById(post.getId());
			
			if (!CollectionUtils.isEmpty(fileList)) {
				// 파일 삭제
				try {
					String path = fileList.get(0).getFilePath();
					for (File file : fileList) {
						fileManagerService.deleteFile(file.getFilePath());
					}
					fileManagerService.deleteFolder(path);
					// DB 파일 삭제
					fileBO.deleteFileById(post.getId());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			postCnt = postDAO.updatePost(post);
		}
		
		return postCnt;
	}
	
	public int deletePostById(int id) {
		int cnt = 0;
		List<File> fileList = fileBO.getFileListById(id);
		
		if (!CollectionUtils.isEmpty(fileList)) {
			// 파일 삭제
			try {
				String path = fileList.get(0).getFilePath();
				for (File file : fileList) {
					fileManagerService.deleteFile(file.getFilePath());
				}
				fileManagerService.deleteFolder(path);
				// DB 파일 삭제
				fileBO.deleteFileById(id);
				cnt = postDAO.deletePostById(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
}
