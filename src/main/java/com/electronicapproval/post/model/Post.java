package com.electronicapproval.post.model;

import java.util.Date;

import lombok.Data;

@Data
public class Post {

	private int ROWNUM;
	private int id;
	private int employeeId;
	private String title;
	private String content;
	private Date createdAt;
	private Date updatedAt;
}
