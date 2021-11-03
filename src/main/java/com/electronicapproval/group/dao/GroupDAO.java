package com.electronicapproval.group.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.electronicapproval.group.model.Group;

@Repository
public interface GroupDAO {
	
	public List<Group> selectGroupListPage(
			@Param("startRow") int startRow
			, @Param("endRow") int endRow);

	public List<Group> selectGroupList();
	
	public int selectGroupListCount();
	
	public Group selectGroupById(int id);
	
	public List<Group> selectGroupListById(int id);
	
	public List<Group> selectTopGroupByLevelList(int level);
	
	public int insertGroup(Group group);

	public int updateGroup(Group group);
	
	public int deleteGroup(int id);
}
