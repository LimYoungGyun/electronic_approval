package com.electronicapproval.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public final static String FILE_UPLOAD_PATH = "D:\\marondal\\0_electronic_approval_project\\electronic_approval\\files/";
	public final static String IMAGE_UPLOAD_PATH = "D:\\marondal\\0_electronic_approval_project\\electronic_approval\\images/";
	
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
	
	// 파일 삭제
	public void deleteFile(String filesPath) throws IOException {
		Path path = Paths.get(FILE_UPLOAD_PATH + filesPath.replace("/files/", ""));
		
		if (Files.exists(path)) {
			// 파일이 존재하면 삭제한다.
			Files.delete(path);
		}
		
	}
	
	// 파일 디렉토리 삭제
	public void deleteFolder(String filesPath) throws IOException {
		Path path = Paths.get(FILE_UPLOAD_PATH + filesPath.replace("/files/", ""));
		
		// 디렉토리 삭제
		path = path.getParent();
		if (Files.exists(path)) {
			// 디렉토리가 존재하면 삭제한다.
			Files.delete(path);
		}
	}
	
	// 이미지 업로드
	public String saveImageFile(String employeeEmail, MultipartFile file) throws IOException {
		String directoryName = employeeEmail + "_" + System.currentTimeMillis() + "/";
		String filePath = IMAGE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			logger.error("[파일업로드] 디렉토리 생성 실패 " + directoryName + ", filePath : " + filePath);
			return null;
		}
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(filePath + file.getOriginalFilename()); // input에 올린 파일명이다.
		Files.write(path, bytes);
		
		return "/images/" + directoryName + file.getOriginalFilename();
	}
	
	// 이미지 삭제
	public void deleteImageFile(String imagePath) throws IOException {
		// 파라미터 : /images/qwer_164564321123/apple.png ---> qwer_164564321123/apple.png
		// 실제 경로 : D:\\marondal\\6_spring_project\\ex\\memo_workspace\\Memo\\images/
		// 실제 경로 + 파라미터 => images가 겹치기 때문에 한쪽(파라미터) /images/를 제거해줌
		
		Path path = Paths.get(IMAGE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		
		if (Files.exists(path)) {
			// 파일이 존재하면 삭제한다.
			Files.delete(path);
		}
		
		// 디렉토리 삭제
		path = path.getParent();
		if (Files.exists(path)) {
			// 디렉토리가 존재하면 삭제한다.
			Files.delete(path);
		}
	}
}
