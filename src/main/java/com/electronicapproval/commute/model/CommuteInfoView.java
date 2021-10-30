package com.electronicapproval.commute.model;

import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.position.model.Position;

import lombok.Data;

@Data
public class CommuteInfoView {

	private Commute commute;
	private Employee employee;
	private Position position;
}
