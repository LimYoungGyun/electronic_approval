package com.electronicapproval.employee.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.electronicapproval.employee.model.Employee;

@Repository
public interface EmployeeDAO {

	public Employee selectEmployeeByEmailAndPassword(
			@Param("email") String email
			, @Param("password") String password);
}
