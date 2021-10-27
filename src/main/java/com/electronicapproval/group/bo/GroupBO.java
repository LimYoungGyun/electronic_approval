package com.electronicapproval.group.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicapproval.group.dao.GroupDAO;
import com.electronicapproval.group.model.Group;

@Service
public class GroupBO {
	
	@Autowired
	private GroupDAO groupDAO;

	public List<Group> getGroupList() {
		return groupDAO.selectGroupList();
	}
	
	public Group getGroupById(int id) {
		return groupDAO.selectGroupById(id);
	}
	
	public int addGroup(String groupName, int topLevelId, String content) {
		
		// topLevel, level, topGroupName을 가져오기 위한 쿼리
		Group group = getGroupById(topLevelId);
		
		if (group == null) {
			group = new Group(0, -1, 0, "-", groupName, content);
		}
		else {
			group.setTopId(topLevelId);
			group.setTopLevel(group.getLevel());
			group.setTopGroupName(group.getGroupName());
			
			// 위의 level을 가지고 다른 컬럼에서 where topLevel을 조회해서 level을 구한다.
			int level = groupDAO.selectTopGroupByLevelList(group.getLevel()).size() + 1;
			
			group.setLevel(level);
			group.setGroupName(groupName);
			group.setContent(content);
		}
		
		return groupDAO.insertGroup(group);
	}
	
	public int updateGroup(int groupId, String groupName, int topLevelId, String content) {
		
		// 상위에 있는 데이터를 topLevelId를 통해 상위 정보를 가져온다.
		Group topGroup = getGroupById(topLevelId);
		
		// 상위의 level, groupName을 데이터를 수정할 Group객체에 담는다.
		Group group = new Group();
		group.setTopLevel(topGroup.getLevel());
		group.setTopGroupName(topGroup.getGroupName());
		
		
		// 위의 level을 가지고 다른 컬럼에서 where topLevel을 조회해서 level을 구한다.
		int level = groupDAO.selectTopGroupByLevelList(group.getLevel()).size() + 1;
		
		group.setTopId(topLevelId);
		group.setId(groupId);
		group.setLevel(level);
		group.setGroupName(groupName);
		group.setContent(content);
		
		return groupDAO.updateGroup(group);
	}
	
	public int deleteGroup(int id) {
		return groupDAO.deleteGroup(id);
	}
}
