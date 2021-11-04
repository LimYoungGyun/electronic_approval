package com.electronicapproval.commute.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.electronicapproval.commute.model.Commute;

@Repository
public interface CommuteDAO {

	public List<Commute> selectCommuteListPage(
			@Param("startDate") String startDate
			, @Param("endDate") String endDate
			, @Param("startRow") int startRow
			, @Param("endRow") int endRow);
	
	public List<Commute> selectCommuteList(
			@Param("startDate") String startDate
			, @Param("endDate") String endDate);
	
	public int selectCommuteListCount(
			@Param("startDate") String startDate
			, @Param("endDate") String endDate);
	
	public Commute selectCommuteByIdAndDates(
			@Param("employeeId") int employeeId
			, @Param("startDate") String startDate
			, @Param("endDate") String endDate);
	
	public Commute selectCommuteById(int employeeId);
	
	public int insertAttendanceTime(
			@Param("employeeId") int employeeId
			, @Param("positionId") int positionId);
//	public int insertAttendanceTime(Commute commute);
	
	public int updateQuittingTime(
//			@Param("commute") Commute commute
			@Param("quittingTime") LocalDateTime quittingTime
			, @Param("overTime") String overTime
			, @Param("employeeId") int employeeId
			, @Param("attendanceTime") Date attendanceTime);
}
