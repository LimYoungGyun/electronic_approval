package com.electronicapproval.employee.model;

import java.util.Date;

import lombok.Data;

@Data
public class Employee {
	
	private int ROWNUM;
	private int id;
	private String name;
	private String email;
	private String password;
	private String residentNumber;
	private String dateHired;
	private int totAnnualLeave;
	private Integer useAnnualLeave;
	private Integer remainAnnualLeave;
	private int groupId;
	private int positionId;
	private Integer officialId;
	private int annualIncome;
	private int salary;
	private String profilePath;
	private String authorityPost;
	private String authorityGroup;
	private String authorityEmployee;
	private String authorityCommute;
	private String authorityForm;
	private Date createdAt;
	private Date updatedAt;
}
