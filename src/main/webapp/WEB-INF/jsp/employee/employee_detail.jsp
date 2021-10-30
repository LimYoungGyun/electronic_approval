<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/static/css/employee.css">
<script src="/static/js/common.js"></script>
<div class="page-content-size">
	<div class="contents box">
		<div class="content">
<!-- 				<div id="profile" class="profile"><img src="/static/image/user.png" alt=""></div> -->
			<table class="table table-bordered detailTable">
				<tr>
<!-- 					<td rowspan="3" class="image"><img src="https://s3-ap-northeast-2.amazonaws.com/images-kr.girlstyle.com/wp-content/uploads/2019/05/ss.jpg" alt="" class="testImage"></td> -->
					<td rowspan="3" class="image"><img src="${employeeInfoView.employee.profilePath}" alt="" class="testImage"></td>
					<th>이름</th>
					<td>${employeeInfoView.employee.name}</td>
					<th>부서</th>
					<td>${employeeInfoView.group.groupName}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${employeeInfoView.employee.email}</td>
					<th>직급</th>
					<td>${employeeInfoView.position.name}</td>
				</tr>
				<tr>
					<th>입사일</th>
					<td>${employeeInfoView.employee.dateHired}</td>
					<th>직책</th>
					<td>${employeeInfoView.official.name}</td>
				</tr>
			</table>
			<table class="table table-bordered detailTable2">
				<tr>
					<th>총 연차수</th>
					<td>${employeeInfoView.employee.totAnnualLeave}</td>
					<th>사용 연차수</th>
					<td>${employeeInfoView.employee.useAnnualLeave}</td>
					<th>남은 연차수</th>
					<td>${employeeInfoView.employee.remainAnnualLeave}</td>
				</tr>
				
				<c:if test="${authorityEmployee == 'WR' || employeeId == employeeInfoView.employee.id}">
					<tr>
						<th>연봉</th>
						<td class="annualIncome"></td>
						<th>월급</th>
						<td colspan="3" class="salary"></td>
					</tr>
				</c:if>
				
				<c:if test="${authorityEmployee == 'WR'}">
					<tr>
						<th>공지사항 권한</th>
						<td class="authorityPost"></td>
						<th>그룹관리 권한</th>
						<td class="authorityGroup"></td>
						<th>조직관리 권한</th>
						<td class="authorityEmployee"></td>
					</tr>
					<tr>
						<th>출퇴근관리 권한</th>
						<td class="authorityCommute"></td>
						<th>기안서관리 권한</th>
						<td colspan="3" class="authorityForm"></td>
					</tr>
				</c:if>
			</table>
		</div>
		
		
		<div class="allButtonLine">
			<div class="delete">
				<c:if test="${authorityGroup == 'WR'}">
					<button type="button" class="employeeDeleteBtn btn btn-danger">삭제</button>
				</c:if>
			</div>
			<div class="buttonLine inputpage">
				<button type="button" class="employeeListBtn btn btn-secondary">목록</button>
				<c:if test="${authorityEmployee == 'WR' || employeeInfoView.employee.id == employeeId}">
					<button type="button" class="employeeDetailBtn btn btn-success">수정</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('직원 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Employee').addClass('active');
		
		// 연봉 ","
		$('.annualIncome').html(comma(${employeeInfoView.employee.annualIncome}));
		
		// 월급 ","
		$('.salary').html(comma(${employeeInfoView.employee.salary}));
		
		// 권한 이름 표기
		$('.authorityPost').html(authority('${employeeInfoView.employee.authorityPost}'));
		$('.authorityGroup').html(authority('${employeeInfoView.employee.authorityGroup}'));
		$('.authorityEmployee').html(authority('${employeeInfoView.employee.authorityEmployee}'));
		$('.authorityCommute').html(authority('${employeeInfoView.employee.authorityCommute}'));
		$('.authorityForm').html(authority('${employeeInfoView.employee.authorityForm}'));
		
		function authority(str) {
			str = String(str);
			if (str == "R") {
				str = '읽기';
			} else if (str == "WR") {
				str = '읽기, 쓰기';
			} else if (str == "P") {
				str = '일부 보기';
			} else {
				str = '전체 보기';
			}
			return str;
		}
		
		// 직원 삭제
		$('.employeeDeleteBtn').on('click', function() {
			let check = confirm('해당 직원을 삭제하시겠습니까??');
			if (check) {
				$.ajax({
					type: 'DELETE'
					, url: '/employee/delete'
					, data: {
						'id' : ${employeeInfoView.employee.id}
						, 'profilePath' : '${employeeInfoView.employee.profilePath}'
					}
					, success:function(data) {
						if (data.result == 'success') {
							alert('직원 정보 삭제 완료');
							location.href='/employee/employee_list_view';
						}
					}
					, error:function(e) {
						alert('직원 정보 삭제 에러발생 : ' + e);
					}
				});
			}
		});
		
		// 직원관리 리스트 페이지로 이동.
		$('.employeeListBtn').on('click', function() {
			location.href='/employee/employee_list_view';
		});
		
		// 직원관리 수정 페이지로 이동.
		$('.employeeDetailBtn').on('click', function() {
			location.href='/employee/employee_update_view?employeeId='+ ${employeeInfoView.employee.id};
		});
	});
</script>