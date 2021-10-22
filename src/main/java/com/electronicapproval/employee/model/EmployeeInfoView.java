package com.electronicapproval.employee.model;

import com.electronicapproval.group.model.Group;
import com.electronicapproval.official.model.Official;
import com.electronicapproval.position.model.Position;

import lombok.Data;

@Data
public class EmployeeInfoView {

	private Employee employee;
	private Group group;
	private Position position;
	private Official official;
}
