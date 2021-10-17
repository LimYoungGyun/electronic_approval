<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <link rel="stylesheet" href="/static/css/common.css"> -->
<link rel="stylesheet" href="/static/css/post.css">
<div class="page-content-size">
	<div class="contents box">
<!-- 		<div class="title">공지사항</div> -->
		<form id="postInsertForm" action="/user/sign_in" method="POST">
			<div class="content">
				<label for="title">제목</label>
				<input type="text" id="title" name="title" class="form-control" required>
				
				<label for="content">내용</label>
				<textarea id="content" name="content" class="form-control" rows="15" required></textarea>
				
				<label for="file">파일첨부</label>
				<input type="file" id="file" name="file" class="form-control">
			</div>
			<div class="buttonLine inputpage">
				<button type="submit" class="postRegistBtn btn btn-success">등록</button>
			</div>
		</form>
	</div>

</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('공지사항');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Dashboard').addClass('active');
		
		// 공지사항 등록
		$('#postInsertForm').submit(function(e) {
			e.preventDefault();
			
			let title = $('#title').val();
			let content = $('#content').val();
			
// 			console.log(title);
// 			console.log(content);
			$.ajax({
				type: 'POST'
				, url: '/post/insert'
				, data: {
					'title':title
					, 'content':content
				}
				, success:function(data) {
					if (data.result == 'success') {
						alert('공지사항 등록 완료');
						location.href='/post/post_list_view';
					}
				}
				, error:function(e) {
					alert('공지사항 등록 에러발생 : ' + e);
				}
			});
			
		});
	});
</script>