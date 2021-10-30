<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sidebar-button">
	<i class='bx bx-menu sidebarBtn'></i> <span class="dashboard"></span>
</div>
<div class="nav-line">
	<button id="attendanceTime" class="btn btn-primary">출근</button>
	<button id="quittingTime" class="btn btn-primary d-none">퇴근</button>
	<div class="profile-details">
		<img src="${profilePath}" alt=""> 
		<span class="employee_name"> ${employeeName} <${employeePosition}></span> <i class='bx bx-chevron-down'></i>
	</div>
</div>
<script>
	$(document).ready(function() {
		let commuteStatus = ${commuteStatus};
		if (commuteStatus) {
			$('#attendanceTime').addClass('d-none');
			$('#quittingTime').removeClass('d-none');
		}
		
		$('#attendanceTime').on('click', function() {
			let urlPath = window.location.pathname;
			$.ajax({
				type: 'POST'
				, url: '/commute/commute_attendanceTime'
				, data:{'urlPath':urlPath}
				, success:function(data) {
					if (data.result == 'success') {
						alert('출근 완료');
						location.href = urlPath;
					}
				}
				, error:function(e) {
					alert('출근 버튼 에러발생 : ' + e);
				}
			});
		});
		$('#quittingTime').on('click', function() {
			alert('퇴근!!! 버튼 기능 없어요');
			let urlPath = window.location.pathname;
			$.ajax({
				type: 'PUT'
				, url: '/commute/commute_quittingTime'
				, data:{'urlPath':urlPath}
				, success:function(data) {
					if (data.result == 'success') {
						alert('퇴근 완료');
						location.href = urlPath;
					}
				}
				, error:function(e) {
					alert('퇴근 버튼 에러발생 : ' + e);
				}
			});
		});
	});
</script>