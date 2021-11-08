<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/static/css/employee.css">
<div class="page-content-size">
	<div class="contents box">
		<div class="content">
			<table id="tableList" class="table table-hover text-center">
				<thead>
					<tr>
						<th class="col-1" scope="col">No.</th>
						<th class="col-2" scope="col">이름</th>
						<th class="col-3" scope="col">이메일</th>
						<th class="col-1" scope="col">부서</th>
						<th class="col-1" scope="col">직급</th>
						<th class="col-1" scope="col">직책</th>
						<th class="col-3" scope="col">입사일</th>
						<th class="d-none">id</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty employeeInfoViewList}">
						<tr>
							<td colspan="7">조회된 직원 정보가 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty employeeInfoViewList}">
						<c:forEach items="${employeeInfoViewList}" var="employeeInfoView" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td class="text-left">${employeeInfoView.employee.name}</td>
								<td class="text-left">${employeeInfoView.employee.email}</td>
								<td>${employeeInfoView.group.groupName}</td>
								<td>${employeeInfoView.official.name}</td>
								<td>${employeeInfoView.position.name}</td>
								<td><fmt:formatDate value="${employeeInfoView.employee.updatedAt}" pattern="yyyy-MM-dd" var="updatedAt"/>${updatedAt}</td>
								<td class="d-none">${employeeInfoView.employee.id}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div class="paging d-flex justify-content-center mt-5">
				<c:if test="${pageMaker.prev ne false}">
					<a href="/employee/employee_list_view?page=${pageMaker.startPage - 1}" class="mr-5">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1">
					<a href="/employee/employee_list_view?page=${i}" id="pageNumber" class="mr-5 pageNumber page${i}" data-page-num="${i}">${i}</a>
				</c:forEach>
				<c:if test="${pageMaker.next ne false}">
					<a href="/employee/employee_list_view?page=${pageMaker.endPage + 1}">[다음]</a>
				</c:if>
			</div>
		</div>
		<c:if test="${authorityEmployee == 'WR'}">
			<div class="buttonLine">
				<button type="button" class="employeeRegistViewBtn btn btn-success">등록</button>
			</div>
		</c:if>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('직원 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Employee').addClass('active');
		
		// 현재 페이지 번호 표시(bold)
		let pageNum = ${paging.page};
		$('.page' + pageNum).addClass('font-weight-bold');
		
		// 등록화면으로 이동
		$('.employeeRegistViewBtn').on('click', function() {
			location.href = '/employee/employee_insert_view';
		});
		
		// 상세화면으로 이동
		// row 데이터 가져오기
		$("#tableList tr").click(function(){ 	
			let str = '';
			
			// 현재 클릭된 Row(<tr>)
			let tr = $(this);
			let td = tr.children();
			
			// td.eq(index)를 통해 값을 가져올 수도 있다.
			let employeeId = td.eq(7).text();
			
			// 숫자가 아닌값 확인.
			if (!$.isNumeric(employeeId)) {
				return;
			}
			
			location.href='/employee/employee_detail_view?employeeId='+employeeId;
		});
		
	});
</script>