<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/static/css/employee.css">
<script src="/static/js/common.js"></script>
<div class="page-content-size">
	<div class="contents box">
		<form id="emploayUpdateForm" action="/employee/employee_update">
			<div class="content">
				<div class="inputcontent">
					<div class="group-left">
						<label for="name">이름</label>
						<input type="text" id="name" name="name" class="form-control" value="${employeeInfoView.employee.name}" autocomplete="off" required>
					</div>
					<div class="group-right">
						<label for="residentNumber">주민등록번호</label>
						<input type="text" id="residentNumber" name="residentNumber" class="form-control" maxlength='14' autocomplete="off" required>
					</div>
				</div>
				
				<div class="inputcontent">
					<div class="group-left">
						<label for="email">이메일 <span class="email-check small text-danger d-none">(이메일주소가 중복되었습니다.)</span></label>
						<input type="email" id="email" name="email" class="form-control" value="${employeeInfoView.employee.email}" autocomplete="off" required>
					</div>
						
					<div class="group-right">
						<label for="password">비밀번호 <span class="myPassword small text-danger d-none">(변경할 비밀번호를 입력하세요.)</span></label>
						<input type="password" id="password" name="password" class="form-control" autocomplete="off" disabled>
					</div>
				</div>
				
				<div class="inputcontent">
					<div class="group-left">
						<label for="">입사일</label>
						<input type="text" id="dateHired" name="dateHired" class="form-control" placeholder="입사일을 선택해 주세요." value="${employeeInfoView.employee.dateHired}" autocomplete="off" required>
					</div>
					<div class="group-right">
						<label for="totAnnualLeave">총 연차 수</label>
						<input type="text" id="totAnnualLeave" name="totAnnualLeave" class="form-control" value="${employeeInfoView.employee.totAnnualLeave}" maxlength='2' autocomplete="off" required>
					</div>
				</div>
				
				<div class="inputcontent">
					<div class="group-left">
						<label for="useAnnualLeave">사용 연차 수</label>
						<input type="text" id="useAnnualLeave" name="useAnnualLeave" class="form-control" value="${employeeInfoView.employee.useAnnualLeave}" maxlength='2' autocomplete="off">
					</div>
					<div class="group-right">
						<label for="remainAnnualLeave">남은 연차 수</label>
						<input type="text" id="remainAnnualLeave" name="remainAnnualLeave" class="form-control" value="${employeeInfoView.employee.remainAnnualLeave}" maxlength='2' autocomplete="off">
					</div>
				</div>
				
				<div class="inputcontent">
					<div class="group-left">
						<label for="">연봉</label>
						<input type="text" id="annualIncome" name="annualIncome" class="form-control" maxlength='13' autocomplete="off" required>
					</div>
					<div class="group-right">
						<label for="salary">월급</label>
						<input type="text" id="salary" name="salary" class="form-control" value="0" autocomplete="off" disabled>
					</div>
				</div>
				<c:if test="${authorityEmployee == 'WR'}">
				<div class="inputcontent">
					<div class="group-left">
						<label for="profile">사진</label>
						<input type="file" id="profile" name="profile" class="form-control d-none" accept=".gif, .jpg, .png, .jpeg" required>
					
						<div class="fileUpdate form-control">
							<button type="button" class="fileSelect"> 파일 선택</button>
							<c:if test="${not empty employeeInfoView.employee.profilePath}">
								<c:set var="filePrint" value="${fn:split(employeeInfoView.employee.profilePath, '/')}" />
								<span class="fileText">${filePrint[fn:length(filePrint)-1]} </span><br>
							</c:if>
						</div>
					</div>
					<button type="button" class="fileDeleteBtn btn btn-danger">파일 삭제</button>
					
				</div>
				<div class="inputcontent">
					<div class="group-left">
						<label for="groupId">부서</label>
						<select id="groupId" name="groupId" class="form-control" required>
							<option value="">-- 그룹을 선택하세요 -- </option>
							<c:forEach items="${groupList}" var="group">
								<option value="${group.id}">${group.groupName}</option>
							</c:forEach>
						</select>
						
						<label for="positionId">직급</label>
						<select id="positionId" name="positionId" class="form-control" required>
							<option value="">-- 직급을 선택하세요 -- </option>
							<c:forEach items="${positionList}" var="position">
								<option value="${position.id}">${position.name}</option>
							</c:forEach>
						</select>
						
						<label for="officialId">직책</label>
						<select id="officialId" name="officialId" class="form-control">
							<option value="">-- 직책을 선택하세요 -- </option>
							<c:forEach items="${officialList}" var="official">
								<option value="${official.id}">${official.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="group-right">
						<label for="">권한</label>
						<div class="radioAreaGroup">
							<div class="radioArea">
								<span>공지사항</span>
								<input type="radio" id="authorityPostR" name="authorityPost" value="R" checked><label for="authorityPostR">읽기</label>
								<input type="radio" id="authorityPostWR" name="authorityPost" value="WR" ><label for="authorityPostWR">읽기, 쓰기</label>
							</div>
							<div class="radioArea">
								<span>그룹관리</span>
								<input type="radio" id="authorityGroupR" name="authorityGroup" value="R"  checked><label for="authorityGroupR">읽기</label>
								<input type="radio" id="authorityGroupWR" name="authorityGroup" value="WR" ><label for="authorityGroupWR">읽기, 쓰기</label>
							</div>
							<div class="radioArea">
								<span>조직관리</span>
								<input type="radio" id="authorityEmployeeR" name="authorityEmployee" value="R"  checked><label for="authorityEmployeeR">읽기</label>
								<input type="radio" id="authorityEmployeeWR" name="authorityEmployee" value="WR" ><label for="authorityEmployeeWR">읽기, 쓰기</label>
							</div>
							<div class="radioArea">
								<span>출퇴근 관리</span>
								<input type="radio" id="authorityCommuteR" name="authorityCommute" value="R"  checked><label for="authorityCommuteR">읽기</label>
								<input type="radio" id="authorityCommuteWR" name="authorityCommute" value="WR" ><label for="authorityCommuteWR">읽기, 쓰기</label>
							</div>
							<div class="radioArea">
								<span>기안서 관리</span>
								<input type="radio" id="authorityFormP" name="authorityForm" value="P"  checked><label for="authorityFormP">일부 보기</label>
								<input type="radio" id="authorityFormA" name="authorityForm" value="WR" ><label for="authorityFormA">전체 보기</label>
							</div>
						</div>
					</div>
				</div>
				</c:if>
			</div>
			
			<c:if test="${authorityEmployee == 'WR' || employeeInfoView.employee.id == employeeId}">
				<div class="buttonLine inputpage">
					<button type="submit" class="employeeUpdateBtn btn btn-success">수정</button>
				</div>
			</c:if>
		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('직원 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Employee').addClass('active');
		
		$( "#dateHired" ).datepicker({
			dateFormat: 'yy-mm-dd'
			, prevText: '이전 달'
			, nextText: '다음 달'
			, showButtonPanel: true
			, currentText: "오늘"
			, closeText: "닫기"
			, monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
			, dayNamesMin: ['일', '월', '화', '수', '목', '금', '토']
			, showMonthAfterYear: true
			, yearSuffix: '년'
		});
		
		$.datepicker._gotoToday = function(id) {
			$(id).datepicker('setDate', new Date()).datepicker('hide').blur();
		};
		
		// 데이터 불러오기
		if (${employeeInfoView.employee.id == employeeId}) {
			$('.myPassword').removeClass('d-none');
			$('#password').prop('disabled', false);
		}
		$('#residentNumber').val(apostrophe(${employeeInfoView.employee.residentNumber}));
		$('#annualIncome').val(comma(${employeeInfoView.employee.annualIncome}));
		$('#salary').val(comma(${employeeInfoView.employee.salary}));
		$('#groupId').val(comma(${employeeInfoView.employee.groupId}));
		$('#positionId').val(${employeeInfoView.employee.positionId});
		$('#officialId').val(${employeeInfoView.employee.officialId});
		
		if ('${employeeInfoView.employee.authorityPost}' == "R") {
			$('#authorityPostR').prop('checked', true);
		} else {
			$('#authorityPostWR').prop('checked', true);
		}
		if ('${employeeInfoView.employee.authorityGroup}' == "R") {
			$('#authorityGroupR').prop('checked', true);
		} else {
			$('#authorityGroupWR').prop('checked', true);
		}
		if ('${employeeInfoView.employee.authorityEmployee}' == "R") {
			$('#authorityEmployeeR').prop('checked', true);
		} else {
			$('#authorityEmployeeWR').prop('checked', true);
		}
		if ('${employeeInfoView.employee.authorityCommute}' == "R") {
			$('#authorityCommuteR').prop('checked', true);
		} else {
			$('#authorityCommuteWR').prop('checked', true);
		}
		if ('${employeeInfoView.employee.authorityForm}' == "P") {
			$('#authorityFormP').prop('checked', true);
		} else {
			$('#authorityFormA').prop('checked', true);
		}
		if ($('.fileUpdate').text() != '') {
			$('#profile').prop('required', false);
		}
		
		if (${authorityEmployee != 'WR'}) {
			$('#name').prop('disabled', true);
			$('#residentNumber').prop('disabled', true);
			$('#dateHired').prop('disabled', true);
			$('#totAnnualLeave').prop('disabled', true);
			$('#useAnnualLeave').prop('disabled', true);
			$('#remainAnnualLeave').prop('disabled', true);
			$('#annualIncome').prop('disabled', true);
		}
		
		// 첨부파일 클릭 이벤트
		$('.fileUpdate').on('click', function() {
			$('#profile').click();
		});
		// 첨부파일 change시 이벤트
		$('#profile').on('change', function() {
			$(this).removeClass('d-none');
			$('.fileUpdate').text('');
			$('.fileUpdate').addClass('d-none');
			$('.fileDeleteBtn').removeClass('d-none');
		});
		
		// 파일 삭제
		$('.fileDeleteBtn').on('click', function() {
			$('.fileUpdate').text('');
			$('.fileUpdate').addClass('d-none');
			$('#profile').val('');
			$('#profile').removeClass('d-none');
			$('#profile').prop('required', true);
			$(this).addClass('d-none');
		});
		
		// 이름 한글과 영어만 입력
		$('#name').on('input', function() {
			$(this).val(onlyString($(this).val()));
		});
		
		// 주민등록번호 "-" 자동 입력
		$('#residentNumber').on('input', function() {
			$(this).val( apostrophe(onlyNumber($(this).val())));
		});
		
		// 연차 숫자만 입력
		$('#totAnnualLeave').on('input', function() {
			$(this).val(onlyNumber($(this).val()));
		});
		
		// 연봉 입력시 월급 자동 입력
		$('#annualIncome').on('input', function() {
			$(this).val(onlyNumber($(this).val()));
			let salary = Math.ceil($('#annualIncome').val() / 12); // 소수점은 0.1이라도 무조건 올림.
			$(this).val(comma($(this).val()));
			$('#salary').val(comma(salary));
		});
		
		// 이미지 첨부 제한
		$('#profile').on('change', function(e) {
			let fileName = e.target.files[0].name;
			let fileNameArr = fileName.split('.');
			if (fileNameArr[fileNameArr.length - 1].toUpperCase() != 'PNG'
				&& fileNameArr[fileNameArr.length - 1].toUpperCase() != 'GIF'
				&& fileNameArr[fileNameArr.length - 1].toUpperCase() != 'JPG'
				&& fileNameArr[fileNameArr.length - 1].toUpperCase() != 'JPEG') {
				alert('이미지 파일만 업로드 할 수 있습니다.');
				$(this).val('');
				return;
			}
		});
		
		// 직원 등록
		$('#emploayUpdateForm').submit(function(e) {
			e.preventDefault();
			
			if (unapostrophe($('#residentNumber').val()).length < 13) {
				alert('주민등록번호를 확인해주세요.');
				$('#residentNumber').focus();
				return;
			}
			
			if (parseInt($('#totAnnualLeave').val()) < parseInt($('#useAnnualLeave').val()) + parseInt($('#remainAnnualLeave').val())) {
				alert('연차 수를 확인해 주세요.');
				$('#totAnnualLeave').focus();
				return;
			}
			
			let formData = new FormData();
			formData.append('id', '${employeeInfoView.employee.id}');
			formData.append('name', $('#name').val());
			formData.append('residentNumber', unapostrophe($('#residentNumber').val()));
			formData.append('email', $('#email').val().trim());
			formData.append('password', $('#password').val());
			formData.append('dateHired', $('#dateHired').val());
			formData.append('totAnnualLeave', $('#totAnnualLeave').val());
			formData.append('useAnnualLeave', $('#useAnnualLeave').val()); // new
			formData.append('remainAnnualLeave', $('#remainAnnualLeave').val()); // new
			formData.append('annualIncome', uncomma($('#annualIncome').val()));
			formData.append('salary', uncomma($('#salary').val()));
			formData.append('file', $('#profile')[0].files[0]);
			let filePath;
			// 불러온 파일 경로
			if ($('.fileUpdate').text() == '') {
				filePath = false;
			} else {
				filePath = true;
			}
			formData.append('filePath', filePath);
			formData.append('groupId', $('#groupId').val());
			formData.append('positionId', $('#positionId').val());
			formData.append('officialId', $('#officialId').val());
			formData.append('authorityPost', $('input:radio[name=authorityPost]:checked').val());
			formData.append('authorityGroup', $('input:radio[name=authorityGroup]:checked').val());
			formData.append('authorityEmployee', $('input:radio[name=authorityEmployee]:checked').val());
			formData.append('authorityCommute', $('input:radio[name=authorityCommute]:checked').val());
			formData.append('authorityForm', $('input:radio[name=authorityForm]:checked').val());
			
			$.ajax({
				type: 'PUT'
				, url: '/employee/employee_update'
				, data: formData
				, enctype : 'multipart/form-data'
				, processData : false
				, contentType : false
				, success:function(data) {
					if (data.result == 'success') {
						alert('직원 정보 수정 완료');
						location.href='/employee/employee_list_view';
					}
					if (data.result == 'Emailoverlap') {
						alert('동일한 이메일이 존재합니다. 이메일을 변경해주세요');
						$('.email-check').removeClass('d-none');
						$('#email').focus();
					}
					
				}
				, error:function(e) {
					alert('직원 등록 에러발생 : ' + e);
				}
			});
		});
	});
</script>
