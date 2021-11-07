package com.electronicapproval.form.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.electronicapproval.form.model.Form;
import com.electronicapproval.form.model.FormInfoView;

@Repository
public interface FormDAO {

	public List<Form> selectFormListByEmployeeId(
			@Param("employeeId") int employeeId
			, @Param("officialId") Integer officialId
			, @Param("startRow") int startRow
			, @Param("endRow") int endRow);
	
	public List<Form> selectFormListByGroupIdAndNowDate(
			@Param("groupId") int groupId
			, @Param("nowDate") String nowDate
			, @Param("status") String status);
	
	public int selectFromListCount(
			@Param("employeeId") int employeeId
			, @Param("officialId") Integer officialId);
	
	public Form selectFormByFormId(int id);
	
	public int insertFrom(Form form);
	
	public int updateFormDisapproval(
			@Param("id") int id
			, @Param("status") String status
			, @Param("reContent") String reContent);
	
	public int updateFormApproval(
			@Param("id") int id
			, @Param("status") String status);
	
	public int updateForm(Form form);
}
