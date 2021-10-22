<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/static/css/employee.css">
<script src="/static/js/common.js"></script>
<div class="page-content-size">
	<div class="contents box">
		<form id="groupInsertForm" action="/group/insert" method="POST">
			<div class="content">
				<div class="inputcontent">
					<div class="group-left">
						<label for="name">이름</label>
						<input type="text" id="name" name="name" class="form-control" autocomplete="off" autofocus required>
					</div>
					<div class="group-right">
						<label for="residentNumber">주민등록번호</label>
						<input type="text" id="residentNumber" name="residentNumber" class="form-control" maxlength='14' autocomplete="off" required>
					</div>
				</div>
				
				<div class="inputcontent">
					<div class="group-left">
						<label for="email">이메일</label>
						<input type="email" id="email" name="email" class="form-control" autocomplete="off" required>
					</div>
						
					<div class="group-right">
						<label for="password">비밀번호 <span class="small text-danger">(초기 비밀번호는 "1234"입니다.)</span></label>
						<input type="password" id="password" name="password" class="form-control" value="1234" autocomplete="off" disabled>
					</div>
				</div>
				
				<div class="inputcontent">
					<div class="group-left">
						<label for="">입사일</label>
						<input type="text" id="dateHired" name="dateHired" class="form-control" placeholder="입사일을 선택해 주세요." required>
					</div>
					<div class="group-right">
						<label for="totAnnualLeave">총 연차 수</label>
						<input type="text" id="totAnnualLeave" name="totAnnualLeave" class="form-control" value="12" maxlength='2' autocomplete="off" required>
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
				
			</div>
			
			<c:if test="${authorityEmployee == 'WR'}">
				<div class="buttonLine inputpage">
					<button type="submit" class="employeeRegistBtn btn btn-success">등록</button>
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
			let salary = Math.floor($('#annualIncome').val() / 12);
			$(this).val(comma($(this).val()));
			$('#salary').val(comma(salary));
		});
		
		// 그룹 등록
// 		$('#employeeRegistBtn').submit(function(e) {
// 			e.preventDefault();
			
			// 주민등록번호 길이 확인
			// 주민등록번호, 연봉, 월급 특수문자 제거하기. (참조한 js파일에 들어있음.)
			// 이메일주소 .trim() 사용해서 가져오기
			
			
			
// 			let groupName = $('#groupName').val();
// 			let topLevel = $('#topLevel').val();
// 			let content = $('#content').val();
			
// 			$.ajax({
// 				type: 'POST'
// 				, url: '/group/insert'
// 				, data: {
// 					'groupName':groupName
// 					, 'topLevelId':topLevel
// 					, 'content':content
// 				}
// 				, success:function(data) {
// 					if (data.result == 'success') {
// 						alert('그룹 등록 완료');
// 						location.href='/group/group_list_view';
// 					}
// 				}
// 				, error:function(e) {
// 					alert('그룹 등록 에러발생 : ' + e);
// 				}
// 			});
// 		});
	});
</script>
