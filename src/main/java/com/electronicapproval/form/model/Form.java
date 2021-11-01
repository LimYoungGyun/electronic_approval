package com.electronicapproval.form.model;

import java.util.Date;

import lombok.Data;

@Data
public class Form {
	private int id;
	private int employeeId;
	private int positionId;
	private int count;
	private Date startDate;
	private Date endDate;
	private int sendTo;
	private String status;
	private Date createdAt;
	private Date updatedAt;
}
