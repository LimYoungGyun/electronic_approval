package com.electronicapproval.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public final static String FILE_UPLOAD_PATH = "D:\\marondal\\0_electronic_approval_project\\electronic_approval\\files/";
	
	// 파일 업로드
	public String saveFile(int postId, MultipartFile file, String directoryName) throws IOException {
		String filePath = "";
		if (directoryName == null) {
			directoryName = postId + "_" + System.currentTimeMillis() + "/";
			filePath = FILE_UPLOAD_PATH + directoryName;
			
			File directory = new File(filePath);
			if (directory.mkdir() == false) {
				logger.error("[파일업로드] 디렉토리 생성 실패 " + directoryName + ", filePath : " + filePath);
				return null;
			}
		} else {
			filePath = FILE_UPLOAD_PATH + directoryName;
		}
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(filePath + file.getOriginalFilename()); // input에 올린 파일명이다.
		Files.write(path, bytes);
		
		return "/files/" + directoryName + file.getOriginalFilename();
	}
}
