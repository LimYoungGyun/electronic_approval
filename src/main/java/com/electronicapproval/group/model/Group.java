package com.electronicapproval.group.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Group {

	private int ROWNUM;
	private int id;
	@NonNull
	private int topId;
	@NonNull
	private int topLevel;
	@NonNull
	private int level;
	@NonNull
	private String topGroupName;
	@NonNull
	private String groupName;
	@NonNull
	private String content;
	private Date createdAt;
	private Date updatedAt;
}
