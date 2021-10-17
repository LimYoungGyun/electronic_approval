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
			<table class="table table-bordered detailTable">
				<tr>
					<th>제목</th>
					<td colspan="4">${post.title}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>나중에 값 불러와야함</td>
					<th>작성일</th>
					<td><fmt:formatDate value="${post.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss" var="updatedAt"/>${updatedAt}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="4">나중에 기능 추가 후 데이터 가져와야함.</td>
				</tr>
			</table>
			<textarea id="content" name="content" class="form-control" rows="15" required>${post.content}</textarea>
		</div>
			<div class="buttonLine inputpage">
				<button type="button" class="postListBtn btn btn-secondary">목록으로</button>
				<c:if test="${authorityPost == 'WR' && post.employeeId eq employeeId}">
					<button type="button" class="postDetailBtn btn btn-success">수정</button>
				</c:if>
			</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('공지사항');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Dashboard').addClass('active');
		
		// 공지사항 리스트 페이지로 이동.
		$('.postListBtn').on('click', function() {
			location.href='/post/post_list_view';
		});
		
		// 공지사항 수정 페이지로 이동.
		$('.postDetailBtn').on('click', function() {
			alert('공지사항 수정 페이지로 이동');
		});
	});
</script>