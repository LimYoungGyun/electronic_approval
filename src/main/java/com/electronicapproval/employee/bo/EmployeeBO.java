package com.electronicapproval.employee.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.electronicapproval.common.FileManagerService;
import com.electronicapproval.employee.dao.EmployeeDAO;
import com.electronicapproval.employee.model.Employee;
import com.electronicapproval.employee.model.EmployeeInfoView;
import com.electronicapproval.group.bo.GroupBO;
import com.electronicapproval.group.model.Group;
import com.electronicapproval.official.bo.OfficialBO;
import com.electronicapproval.official.model.Official;
import com.electronicapproval.position.bo.PositionBO;
import com.electronicapproval.position.model.Position;

@Service
public class EmployeeBO {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private GroupBO groupBO;
	
	@Autowired
	private PositionBO positionBO;
	
	@Autowired
	private OfficialBO officialBO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public List<Employee> getEmployeeList() {
		return employeeDAO.selectEmployeeList();
	}
	
	public List<Employee> getEmployeeListPage(int startRow, int endRow) {
		return employeeDAO.selectEmployeeListPage(startRow, endRow);
	}
	
	public int getEmployeeListCount() {
		return employeeDAO.selectEmployeeListCount();
	}
	
	public List<EmployeeInfoView> employeeInfoViewList(int startRow, int endRow) {
		
		// 전체 내용 저장할 List
		List<EmployeeInfoView> employeeInfoViewList = new ArrayList<>();
		
		// 한 행이 출력할 정보 저장용 List
		List<Employee> employeeList = getEmployeeListPage(startRow, endRow);
		for (Employee employee : employeeList) {
			
			// 전체 내용을 저장할 객체.
			EmployeeInfoView employeeInfoView = new EmployeeInfoView();
			employeeInfoView.setEmployee(employee);;
			
			// 부서(그룹)
			Group group = groupBO.getGroupById(employee.getGroupId());
			employeeInfoView.setGroup(group);
			
			// 직급
			Position position = positionBO.getPositionById(employee.getPositionId());
			employeeInfoView.setPosition(position);
			
			// 직책
			Official official = new Official();
			if (employee.getOfficialId() == null) {
				official.setId(0);
				official.setName("-");
				employeeInfoView.setOfficial(official);
			} else {
				official = officialBO.getOfficialById(employee.getOfficialId());
				employeeInfoView.setOfficial(official);
			}
			
			employeeInfoViewList.add(employeeInfoView);
		}
		
		return employeeInfoViewList;
	}
	
	public EmployeeInfoView getemployeeInfoView(int employeeId) {
		
		// 내용을 저장할 객체 생성.
		EmployeeInfoView employeeInfoView = new EmployeeInfoView();
		
		Employee employee = getEmployeeById(employeeId);
		employeeInfoView.setEmployee(employee);
		
		Group group = groupBO.getGroupById(employee.getGroupId());
		employeeInfoView.setGroup(group);
		
		Position position = positionBO.getPositionById(employee.getPositionId());
		employeeInfoView.setPosition(position);
		
		Official official = new Official();
		if (employee.getOfficialId() == null) {
			official.setId(0);
			official.setName("-");
			employeeInfoView.setOfficial(official);
		} else {
			official = officialBO.getOfficialById(employee.getOfficialId());
			employeeInfoView.setOfficial(official);
		}
		
		return employeeInfoView;
	}

	public Employee getEmployeeByEmailAndPassword(String email, String password) {
		return employeeDAO.selectEmployeeByEmailAndPassword(email, password);
	}
	
	public Employee getEmployeeById(int id) {
		return employeeDAO.selectEmployeeById(id);
	}
	
	public int addEmployeeInsert(Employee employee, MultipartFile file) {
		
		String employeeEmail = employee.getEmail();
		
		if (employeeEmail.contains("@")) {
			String[] employeeIdArr = employeeEmail.split("@");
			employeeEmail = employeeIdArr[0];
		}
		
		employee.setRemainAnnualLeave(employee.getTotAnnualLeave());
		
		String imagePath = null;
		try {
			imagePath = fileManagerService.saveImageFile(employeeEmail, file);
			employee.setProfilePath(imagePath);
		} catch (IOException e) {
			imagePath = null;
		}
		
		return employeeDAO.insertEmployeeInsert(employee);
	}
	
	@Transactional
	public int updateEmployeeByNoPassword(Employee employee, MultipartFile file, Boolean filePath) {
		
		EmployeeInfoView employeeInfoView = getemployeeInfoView(employee.getId());
		
		String employeeEmail = employee.getEmail();
		if (employeeEmail.contains("@")) {
			String[] employeeIdArr = employeeEmail.split("@");
			employeeEmail = employeeIdArr[0];
		}
		
		if (filePath == true) {
			// 기존의 filePath저장
			employee.setProfilePath(employeeInfoView.getEmployee().getProfilePath());
			// update 진행
			
		} else {
			try {
				// 기존의 filePath 삭제
				fileManagerService.deleteImageFile(employeeInfoView.getEmployee().getProfilePath());
				// 새로운 file 등록
				employee.setProfilePath(fileManagerService.saveImageFile(employeeEmail, file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return employeeDAO.updateEmployeeByNoPassword(employee);
	}
	
	@Transactional
	public int updateEmployeeByPassword(Employee employee, MultipartFile file, Boolean filePath) {
		
		EmployeeInfoView employeeInfoView = getemployeeInfoView(employee.getId());
		
		String employeeEmail = employee.getEmail();
		if (employeeEmail.contains("@")) {
			String[] employeeIdArr = employeeEmail.split("@");
			employeeEmail = employeeIdArr[0];
		}
		
		if (filePath == true) {
			// 기존의 filePath저장
			employee.setProfilePath(employeeInfoView.getEmployee().getProfilePath());
			// update 진행
			
		} else {
			try {
				// 기존의 filePath 삭제
				fileManagerService.deleteImageFile(employeeInfoView.getEmployee().getProfilePath());
				// 새로운 file 등록
				employee.setProfilePath(fileManagerService.saveImageFile(employeeEmail, file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return employeeDAO.updateEmployeeByPassword(employee);
	}
	
	public int deleteEmployeeById(int id, String profilePath) {
		try {
			fileManagerService.deleteImageFile(profilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employeeDAO.deleteEmployeeById(id);
	}
}