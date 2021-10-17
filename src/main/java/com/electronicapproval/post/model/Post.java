package com.electronicapproval.post.model;

import java.util.Date;

import lombok.Data;

@Data
public class Post {

	private int id;
	private int employeeId;
	private String title;
	private String content;
	private String filePath;
	private Date createdAt;
	private Date updatedAt;
}