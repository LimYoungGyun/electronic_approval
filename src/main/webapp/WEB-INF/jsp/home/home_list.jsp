<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <div class="sales-boxes"> -->
<div class="postList">
	<div class="contents box">
		<div class="title"><a href="/post/post_list_view">공지사항</a></div>
		<div class="content">
			<table id="tableList" class="table table-hover text-center">
				<thead>
					<tr>
						<th class="col-1" scope="col">No.</th>
						<th class="col-6" scope="col">제목</th>
						<th class="col-2" scope="col">작성자</th>
						<th class="col-3" scope="col">작성날짜</th>
						<th class="col-3 d-none">id</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${postList}" var="post" varStatus="status">
						<tr>
							<td>${postList.size() - (status.count - 1)}</td>
							<td class="text-left">${post.title}</td>
							<td>${emoployeeList.get(status.index)}</td>
							<td><fmt:formatDate value="${post.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss" var="updatedAt"/>${updatedAt}</td>
							<td class="d-none">${post.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	<div class="contents box tableFromList">
		<div class="title"><a href="/form/form_list_view">휴가자 목록</a></div>
		<div class="content">
			<table id="tableFromList" class="table table-hover text-center">
				<thead>
					<tr>
						<th class="col-1" scope="col">번호</th>
						<th class="col-3" scope="col">이름</th>
						<th class="col-2" scope="col">직책</th>
						<th class="col-3" scope="col">시작일자</th>
						<th class="col-3" scope="col">종료일자</th>
						<th class="col-3 d-none">id</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${formList}" var="form" varStatus="status">
						<tr>
							<td>${formList.size() - (status.count - 1)}</td>
							<td>${form.employee.name}</td>
							<td>${form.position.name}</td>
							<td>${form.form.startDate}</td>
							<td>${form.form.endDate}</td>
							<td class="d-none">${form.form.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="top-sales box">
	<div class="title"><a href="/commute/commute_list_view">출 퇴근 현황</a></div>
	<table class="table table-hover text-center">
		<thead>
			<tr>
				<th>이름</th>
				<th>출근시간</th>
				<th>퇴근시간</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${commuteInfoViewList}" var="commuteInfoView" varStatus="status">
				<tr>
					<td>${commuteInfoView.employee.name}</td>
					<c:if test="${not empty commuteInfoView.commute.attendanceTime}">
						<td>출근</td>
					</c:if>
					<c:if test="${empty commuteInfoView.commute.attendanceTime}">
						<td>-</td>
					</c:if>
<%-- 						<td class="attendanceTime"><fmt:formatDate value="${commuteInfoView.commute.attendanceTime}" pattern="HH:mm:ss" var="attendanceTime"/>${attendanceTime}</td> --%>
					<td class="quittingTime"><fmt:formatDate value="${commuteInfoView.commute.quittingTime}" pattern="HH:mm:ss" var="quittingTime"/>${quittingTime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	$(document).ready(function() {
		// titleName
		$('.dashboard').text('전자결재시스템');
		if ($('.nav-links a').hasClass('active')) {
			$('a').removeClass('active');
		}
		
		// 공지사항 상세화면으로 이동
		// row 데이터 가져오기
		$("#tableList tr").click(function(){ 	
			let str = '';
			
			// 현재 클릭된 Row(<tr>)
			let tr = $(this);
			let td = tr.children();
			
			// td.eq(index)를 통해 값을 가져올 수도 있다.
			let postId = td.eq(4).text();
			
			// 숫자가 아닌값 확인.
			if (!$.isNumeric(postId)) {
				return;
			}
			
			console.log(postId);

			location.href='/post/post_detail_view?postId='+postId;
		});
		
		// 휴가자 상세화면으로 이동
		// row 데이터 가져오기
		$("#tableFromList tr").click(function(){ 	
			let str = '';
			
			// 현재 클릭된 Row(<tr>)
			let tr = $(this);
			let td = tr.children();
			
			// td.eq(index)를 통해 값을 가져올 수도 있다.
			let formId = td.eq(5).text();
			
			// 숫자가 아닌값 확인.
			if (!$.isNumeric(formId)) {
				return;
			}
			
			console.log(formId);
			
			location.href='/form/form_detail_view?formId='+formId;
		});
	});
</script>
