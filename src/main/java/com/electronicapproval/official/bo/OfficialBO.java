package com.electronicapproval.official.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicapproval.official.dao.OfficialDAO;
import com.electronicapproval.official.model.Official;

@Service
public class OfficialBO {
	
	@Autowired
	private OfficialDAO officialDAO;
	
	public List<Official> getOfficialList() {
		return officialDAO.selectOfficialList();
	}

	public Official getOfficialById(int id) {
		return officialDAO.selectOfficialById(id);
	}
}
