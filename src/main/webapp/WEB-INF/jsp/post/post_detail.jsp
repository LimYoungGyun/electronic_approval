<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <link rel="stylesheet" href="/static/css/common.css"> --%>
<link rel="stylesheet" href="/static/css/post.css">
<div class="page-content-size">
	<div class="contents box">
		<%-- <div class="title">공지사항</div> --%>
		<div class="content">
			<table class="table table-bordered detailTable">
				<tr>
					<th>제목</th>
					<td colspan="4">${post.title}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${postName }</td>
					<th>작성일</th>
					<td><fmt:formatDate value="${post.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss" var="updatedAt"/>${updatedAt}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="4">
					<c:if test="${not empty postFiles}">
						<c:forEach items="${postFiles}" var="file">
							<a href="/download?filePath=${file.filePath}">
								<c:set var="filePrint" value="${fn:split(file.filePath, '/')}" />
								${filePrint[fn:length(filePrint)-1]} <br>
							</a>
						</c:forEach>
					</c:if>
					</td>
				</tr>
			</table>
			<textarea id="content" name="content" class="form-control" rows="15" disabled>${post.content}</textarea>
		</div>
		<div class="allButtonLine">
			<div class="delete">
				<c:if test="${authorityPost == 'WR' && post.employeeId eq employeeId}">
					<button type="button" class="postDeleteBtn btn btn-danger">삭제</button>
				</c:if>
			</div>
			<div class="buttonLine inputpage">
				<button type="button" class="postListBtn btn btn-secondary">목록</button>
				<c:if test="${authorityPost == 'WR' && post.employeeId eq employeeId}">
					<button type="button" class="postDetailBtn btn btn-success">수정</button>
				</c:if>
			</div>
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
		
		// 게시물 삭제
		$('.postDeleteBtn').on('click', function() {
			let check = confirm('해당 게시물을 삭제하시겠습니까??');
			if (check) {
				$.ajax({
					type: 'DELETE'
					, url: '/post/delete'
					, data: {
						'id' : ${post.id}
					}
					, success:function(data) {
						if (data.result == 'success') {
							alert('공지사항 삭제 완료');
							location.href='/post/post_list_view';
						}
					}
					, error:function(e) {
						alert('공지사항 삭제 에러발생 : ' + e);
					}
				});
			}
		});
		
		// 공지사항 리스트 페이지로 이동.
		$('.postListBtn').on('click', function() {
			location.href='/post/post_list_view';
		});
		
		// 공지사항 수정 페이지로 이동.
		$('.postDetailBtn').on('click', function() {
			location.href='/post/post_update_view?postId='+ ${post.id};
		});
	});
</script>