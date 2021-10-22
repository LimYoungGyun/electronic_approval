package com.electronicapproval.official.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.electronicapproval.official.model.Official;

@Repository
public interface OfficialDAO {
	
	public List<Official> selectOfficialList();

	public Official selectOfficialById(int id);
}
