package com.electronicapproval.commute.bo;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicapproval.commute.dao.CommuteDAO;
import com.electronicapproval.commute.model.Commute;
import com.electronicapproval.commute.model.CommuteInfoView;
import com.electronicapproval.employee.bo.EmployeeBO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.position.bo.PositionBO;
import com.electronicapproval.position.model.Position;

@Service
public class CommuteBO {
	
	@Autowired
	private CommuteDAO commuteDAO;
	
	@Autowired
	private EmployeeBO employeeBO;
	
	@Autowired
	private PositionBO positionBO;

	public List<CommuteInfoView> getCommuteList(String startDate, String endDate) {
		
		List<CommuteInfoView> commuteInfoViewList = new ArrayList<>();
		
		List<Commute> commuteList = commuteDAO.getCommuteList(startDate, endDate);
		for (Commute commute : commuteList) {
			CommuteInfoView commuteInfoView = new CommuteInfoView();
			
			commuteInfoView.setCommute(commute);
			
			Employee employee = employeeBO.getEmployeeById(commute.getEmployeeId());
			commuteInfoView.setEmployee(employee);
			
			Position position = positionBO.getPositionById(commute.getPositionId());
			commuteInfoView.setPosition(position);
			
			commuteInfoViewList.add(commuteInfoView);
		}
		
		return commuteInfoViewList;
	}
	
	public Commute getCommuteById(int employeeId) {
		return commuteDAO.selectCommuteById(employeeId);
	}
	
	public Commute getCommuteByIdAndDates(int employeeId, String startDate, String endDate) {
		return commuteDAO.selectCommuteByIdAndDates(employeeId, startDate, endDate);
	}
	
	public int addAttendanceTime(int employeeId) {
		
		// employeeId로 회원 정보 조회
		Employee employee = employeeBO.getEmployeeById(employeeId);
		// employeeId, positionId넘기기
		return commuteDAO.insertAttendanceTime(employeeId, employee.getPositionId());
	}
	
	public int updateQuittingTime(int employeeId) {
		
		Commute commute = getCommuteById(employeeId);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 05:00:00");
		
		// 출근 시간
		Date getattendanceTime = commute.getAttendanceTime();
		LocalDateTime attendanceTime = new java.sql.Timestamp(getattendanceTime.getTime()) // java.util.Date -> java.sql.Timestamp
				.toLocalDateTime(); // java.sql.Timestamp -> LocalDateTime

		
		// 퇴근 시간
		LocalDateTime quittingTime = LocalDateTime.now();
		LocalDateTime getquittingTime = LocalDateTime.now().minusHours(9); // 야근시간을 계산하기 위한 변수 (퇴근시간 - 9)
		
		// 야근시간
		Duration duration = Duration.between(attendanceTime, getquittingTime);
		
		String overTime = "";
		if (duration.getSeconds() <= 0) {
			overTime = "-";
		} else {
			long hour = duration.getSeconds() / 3600;
			long hour_value = duration.getSeconds() % 3600;
			
			long Min = hour_value / 60;
			long Min_value = hour_value % 60;
			
			long Sec = Min_value % 60;
			
			if (Min < 10) {
				overTime = hour + ":" + "0" + Min + ":" + Sec;
			} else {
				overTime = hour + ":" + Min + ":" + Sec;
			}
		}
		return commuteDAO.updateQuittingTime(quittingTime, overTime, employeeId, commute.getAttendanceTime());
	}
}
