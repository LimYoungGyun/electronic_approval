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
	<div class="contents box">
		<div class="title">휴가자 목록</div>
		<div class="content">
			<table class="table">
				<tr>
					<th>1</th>
					<th>2</th>
					<th>3</th>
					<th>4</th>
				</tr>
				<tr>
					<td>adf</td>
					<td>adf</td>
					<td>asd</td>
					<td>asdf</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<div class="top-sales box">
	<div class="title">출 퇴근 현황</div>
	<table class="table">
		<tr>
			<th>1</th>
			<th>2</th>
			<th>3</th>
			<th>4</th>
		</tr>
		<tr>
			<td>adf</td>
			<td>adf</td>
			<td>asd</td>
			<td>asdf</td>
		</tr>
	</table>
</div>
<script>
	$(document).ready(function() {
		// titleName
		$('.dashboard').text('전자결재시스템');
		if ($('.nav-links a').hasClass('active')) {
			$('a').removeClass('active');
		}
		
		// 상세화면으로 이동
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
	});
</script>
