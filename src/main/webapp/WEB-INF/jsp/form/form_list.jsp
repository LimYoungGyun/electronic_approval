<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/static/css/form.css">
<div class="page-content-size">
	<div class="contents box">
		<div class="content">
			<table id="tableList" class="table table-hover text-center">
				<thead>
					<tr>
						<th class="col-1" scope="col">No.</th>
						<th class="col-1" scope="col">이름</th>
						<th class="col-1" scope="col">직급</th>
						<th class="col-1" scope="col">사용개수</th>
						<th class="col-2" scope="col">연차시작기간</th>
						<th class="col-2" scope="col">연차종료기간</th>
						<th class="col-1" scope="col">상태</th>
						<th class="col-3" scope="col">작성일자</th>
						<th class="d-none">id</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:if test="${empty formInfoViewList}">
							<td colspan="8">기안서 관련 게시물이 존재하지 않습니다.</td>
						</c:if>
					</tr>
					<c:forEach items="${formInfoViewList}" var="form" varStatus="status">
						<tr>
							<c:if test="${not empty formInfoViewList}">
								<td>${paging.totalArticle - ((paging.page - 1) * paging.pageSize) - status.index}</td>
								<td>${form.employee.name}</td>
								<td>${form.position.name}</td>
								<td>${form.form.count}</td>
								<td>${form.form.startDate}</td>
								<td>${form.form.startDate}</td>
								<td>${form.form.status}</td>
								<td><fmt:formatDate value="${form.form.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" var="createdAt"/>${createdAt}</td>
								<td class="d-none">${form.form.id}</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="paging d-flex justify-content-center mt-5">
				<c:if test="${pageMaker.prev ne false}">
					<a href="/form/form_list_view?page=${pageMaker.startPage - 1}" class="mr-5">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1">
					<a href="/form/form_list_view?page=${i}" id="pageNumber" class="mr-5 pageNumber page${i}" data-page-num="${i}">${i}</a>
				</c:forEach>
				<c:if test="${pageMaker.next ne false}">
					<a href="/form/form_list_view?page=${pageMaker.endPage + 1}">[다음]</a>
				</c:if>
			</div>
		</div>
		<div class="buttonLine">
			<button type="button" class="formRegistViewBtn btn btn-success">등록</button>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('기안서 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Form').addClass('active');
		
		// 현재 페이지 번호 표시(bold)
		let pageNum = ${paging.page};
		$('.page' + pageNum).addClass('font-weight-bold');
		
		// 등록화면으로 이동
		$('.formRegistViewBtn').on('click', function() {
			location.href = '/form/form_insert_view';
		});
		
		// 상세화면으로 이동
		// row 데이터 가져오기
		$("#tableList tr").click(function(){ 	
			let str = '';
			
			// 현재 클릭된 Row(<tr>)
			let tr = $(this);
			let td = tr.children();
			
			// td.eq(index)를 통해 값을 가져올 수도 있다.
			let formId = td.eq(8).text();
			
			// 숫자가 아닌값 확인.
			if (!$.isNumeric(formId)) {
				return;
			}
			
			location.href='/form/form_detail_view?formId='+ formId;
		});
		
	});
</script>