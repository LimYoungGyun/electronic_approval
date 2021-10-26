<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <link rel="stylesheet" href="/static/css/common.css"> -->
<link rel="stylesheet" href="/static/css/post.css">
<div class="page-content-size">
	<div class="contents box">
<!-- 		<div class="title">공지사항</div> -->
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
		<c:if test="${authorityPost == 'WR'}">
			<div class="buttonLine">
				<button type="button" class="postRegistViewBtn btn btn-success">등록</button>
			</div>
		</c:if>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('공지사항');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Dashboard').addClass('active');
		
		// 등록화면으로 이동
		$('.postRegistViewBtn').on('click', function() {
			location.href = '/post/post_insert_view';
		});
		
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