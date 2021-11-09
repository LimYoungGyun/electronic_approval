<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/static/css/commute.css">
<div class="page-content-size">
	<div class="contents box">
		<div class="content">
			<div class="dateSelect">
				<label for="date">날짜 : </label>
				<input type="text" id="date" name="date" class="form-control date" value="${searchDate}" readonly>
			</div>
			<table id="tableList" class="table table-hover text-center">
				<thead>
					<tr>
						<th class="col-1" scope="col">No.</th>
						<th class="col-2" scope="col">이름</th>
						<th class="col-1" scope="col">직급</th>
						<th class="col-3" scope="col">출근시간</th>
						<th class="col-3" scope="col">퇴근시간</th>
						<th class="col-3" scope="col">야근시간</th>
						<th class="d-none">id</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty commuteInfoViewList}">
						<tr>
							<td colspan="6">조회된 출퇴근 기록이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty commuteInfoViewList}">
						<c:forEach items="${commuteInfoViewList}" var="commuteInfoView" varStatus="status">
							<tr>
								<td>${commuteInfoViewList.size() - (status.count - 1)}</td>
								<td>${commuteInfoView.employee.name}</td>
								<td>${commuteInfoView.position.name}</td>
								<td class="attendanceTime"><fmt:formatDate value="${commuteInfoView.commute.attendanceTime}" pattern="yyyy-MM-dd HH:mm:ss" var="attendanceTime"/>${attendanceTime}</td>
								<td class="quittingTime">
									<c:if test="${not empty commuteInfoView.commute.quittingTime}">
										<fmt:formatDate value="${commuteInfoView.commute.quittingTime}" pattern="yyyy-MM-dd HH:mm:ss" var="quittingTime"/>${quittingTime}
									</c:if>
									<c:if test="${empty commuteInfoView.commute.quittingTime}">-</c:if>
								</td>
								<td class="overTime">
									<c:if test="${not empty commuteInfoView.commute.overTime}">${commuteInfoView.commute.overTime}</c:if>
									<c:if test="${empty commuteInfoView.commute.overTime}">-</c:if>
								</td>
								<td class="d-none"></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div class="paging d-flex justify-content-center mt-5">
				<c:if test="${pageMaker.prev ne false}">
					<a href="/commute/commute_list_view?page=${pageMaker.startPage - 1}" class="mr-5">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1">
					<a href="/commute/commute_list_view?page=${i}" id="pageNumber" class="mr-5 pageNumber page${i}" data-page-num="${i}">${i}</a>
				</c:forEach>
				<c:if test="${pageMaker.next ne false}">
					<a href="/commute/commute_list_view?page=${pageMaker.endPage + 1}">[다음]</a>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('출 퇴근 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Commute').addClass('active');
		
		// 현재 페이지 번호 표시(bold)
		let pageNum = ${paging.page};
		$('.page' + pageNum).addClass('font-weight-bold');
		
		$( "#date" ).datepicker({
			dateFormat: 'yy-mm-dd'
			, prevText: '이전 달'
			, nextText: '다음 달'
			, monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
			, dayNamesMin: ['일', '월', '화', '수', '목', '금', '토']
			, showMonthAfterYear: true
			, yearSuffix: '년'
		});
		
		$.datepicker._gotoToday = function(id) {
			$(id).datepicker('setDate', new Date()).datepicker('hide').blur();
		};
		
		$('#date').on('change', function() {
			alert($('#date').val());
			let searchDate = $('#date').val();
			location.href='/commute/commute_list_view?searchDate=' + searchDate;
		});
		
		// 상세화면으로 이동
		// row 데이터 가져오기
// 		$("#tableList tr").click(function(){
// 			if ('${authorityCommute}' == 'WR') {
// 				alert('권한있음');
// 			} else {
// 				alert('권한없음.');
// 			}
			
// 			let str = '';
			
// 			// 현재 클릭된 Row(<tr>)
// 			let tr = $(this);
// 			let td = tr.children();
			
// 			// td.eq(index)를 통해 값을 가져올 수도 있다.
// 			let employeeId = td.eq(7).text();
			
// 			// 숫자가 아닌값 확인.
// 			if (!$.isNumeric(employeeId)) {
// 				return;
// 			}
			
// 			location.href='/employee/employee_detail_view?employeeId='+employeeId;
// 		});
		
	});
</script>