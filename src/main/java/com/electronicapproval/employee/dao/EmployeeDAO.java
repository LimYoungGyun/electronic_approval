package com.electronicapproval.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.electronicapproval.employee.model.Employee;

@Repository
public interface EmployeeDAO {
	
	public List<Employee> selectEmployeeList();
	
	public Employee selectEmployeeByEmailAndPassword(
			@Param("email") String email
			, @Param("password") String password);
	
	public Employee selectEmployeeById(int id);
	
	public int insertEmployeeInsert(Employee employee);
	
	public int updateEmployeeByNoPassword(Employee employee);
	
	public int updateEmployeeByPassword(Employee employee);
}
