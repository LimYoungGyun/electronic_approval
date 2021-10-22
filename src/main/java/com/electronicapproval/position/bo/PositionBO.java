package com.electronicapproval.position.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicapproval.position.dao.PositionDAO;
import com.electronicapproval.position.model.Position;

@Service
public class PositionBO {
	
	@Autowired
	private PositionDAO positionDAO;
	
	public List<Position> getPositionList() {
		return positionDAO.selectPositionList();
	}
	
	public Position getPositionById(int id) {
		return positionDAO.selectPositionById(id);
	}

}
