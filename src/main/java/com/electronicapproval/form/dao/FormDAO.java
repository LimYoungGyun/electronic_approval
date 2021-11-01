package com.electronicapproval.form.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.electronicapproval.form.model.Form;

@Repository
public interface FormDAO {

	public List<Form> selectFormListByEmployeeId(
			@Param("employeeId") int employeeId
			, @Param("officialId") Integer officialId);
}
