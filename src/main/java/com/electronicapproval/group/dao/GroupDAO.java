package com.electronicapproval.group.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.electronicapproval.group.model.Group;

@Repository
public interface GroupDAO {

	public List<Group> selectGroupList();
	
	public Group selectGroupById(int id);
	
	public List<Group> selectTopGroupByLevelList(int level);
	
	public int insertGroup(Group group);

	public int updateGroup(Group group);
}
