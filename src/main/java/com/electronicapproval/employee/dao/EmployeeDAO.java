package com.electronicapproval.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.electronicapproval.employee.model.Employee;

@Repository
public interface EmployeeDAO {
	
	public List<Employee> selectEmployeeList();
	
	public Employee selectEmployeeByEmailAndPassword(
			@Param("email") String email
			, @Param("password") String password);
	
	public String selectNameById(int id);
	
	public int insertEmployeeInsert(Employee employee);
}
