package com.electronicapproval.file.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicapproval.file.dao.FileDAO;
import com.electronicapproval.file.model.File;

@Service
public class FileBO {
	
	@Autowired
	private FileDAO fileDAO;

	public int addFiles(int postId, String filePath) {
		return fileDAO.insertFiles(postId, filePath);
	}
	
	public List<File> getFileListById(int postId) {
		return fileDAO.selectFileListById(postId);
	}
	
	public void deleteFileById(int postId) {
		fileDAO.deleteFileById(postId);
	}
	
}
