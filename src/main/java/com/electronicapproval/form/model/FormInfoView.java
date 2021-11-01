package com.electronicapproval.form.model;

import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.position.model.Position;

import lombok.Data;

@Data
public class FormInfoView {
	
	private Form form;
	private Employee employee;
//	private Group group;
	private Position position;
//	private Official official;
}
