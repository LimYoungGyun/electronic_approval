package com.electronicapproval.file.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.electronicapproval.file.model.File;

@Repository
public interface FileDAO {

	public int insertFiles(
			@Param("postId") int postId
			, @Param("filePath") String filePath);
	
	public List<File> selectFileListById(int postId);
	
	public void deleteFileById(int postId);
}
