package com.electronicapproval.position.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.electronicapproval.position.model.Position;

@Service
public interface PositionDAO {
	
	public List<Position> selectPositionList();

	public Position selectPositionById(int id);
}
