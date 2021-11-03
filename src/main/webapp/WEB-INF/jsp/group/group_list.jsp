<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/static/css/group.css">
<div class="page-content-size">
	<div class="contents box">
		<div class="content">
			<table id="tableList" class="table table-hover text-center">
				<thead>
					<tr>
						<th class="col-1" scope="col">No.</th>
						<th class="col-3" scope="col">상위그룹</th>
						<th class="col-3" scope="col">그룹명</th>
						<th class="col-5" scope="col">등록날짜</th>
						<th class="d-none">id</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${groupListPage}" var="group" varStatus="status">
						<tr>
							<td>${((paging.page - 1) * paging.pageSize) + status.count}</td>
							<td class="text-left">${group.topGroupName}</td>
							<td class="text-left">${group.groupName}</td>
							<td><fmt:formatDate value="${group.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss" var="updatedAt"/>${updatedAt}</td>
							<td class="d-none">${group.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="paging d-flex justify-content-center mt-5">
				<c:if test="${pageMaker.prev ne false}">
					<a href="/group/group_list_view?page=${pageMaker.startPage - 1}" class="mr-5">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1">
					<a href="/group/group_list_view?page=${i}" id="pageNumber" class="mr-5 pageNumber page${i}" data-page-num="${i}">${i}</a>
				</c:forEach>
				<c:if test="${pageMaker.next ne false}">
					<a href="/group/group_list_view?page=${pageMaker.endPage + 1}">[다음]</a>
				</c:if>
			</div>
		</div>
		<c:if test="${authorityGroup == 'WR'}">
			<div class="buttonLine">
				<button type="button" class="groupRegistViewBtn btn btn-success">등록</button>
			</div>
		</c:if>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('그룹 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Group').addClass('active');
		
		// 현재 페이지 번호 표시(bold)
		let pageNum = ${paging.page};
		$('.page' + pageNum).addClass('font-weight-bold');
		
		// 등록화면으로 이동
		$('.groupRegistViewBtn').on('click', function() {
			location.href = '/group/group_insert_view';
		});
		
		// 상세화면으로 이동
		// row 데이터 가져오기
		$("#tableList tr").click(function(){ 	
			let str = '';
			
			// 현재 클릭된 Row(<tr>)
			let tr = $(this);
			let td = tr.children();
			
			// td.eq(index)를 통해 값을 가져올 수도 있다.
			let groupId = td.eq(4).text();
			
			// 숫자가 아닌값 확인.
			if (!$.isNumeric(groupId)) {
				return;
			}
			
			location.href='/group/group_detail_view?groupId='+groupId;
		});
		
	});
</script>