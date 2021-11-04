package com.electronicapproval.commute.model;

import java.util.Date;

import lombok.Data;

@Data
public class Commute {
	
	private int ROWNUM;
	private int id;
	private int employeeId;
	private int positionId;
	private Date attendanceTime;
	private Date quittingTime;
	private String overTime;
	private Date createdAt;
	private Date updatedAt;
}
