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
		<form id="postInsertForm" action="/user/sign_in" method="POST">
			<div class="content">
				<label for="title">제목</label>
				<input type="text" id="title" name="title" class="form-control" value="${post.title}" required>
				
				<label for="content">내용</label>
				<textarea id="content" name="content" class="form-control" rows="15" required>${post.content}</textarea>
				
				<label for="file">파일첨부</label>
				<input type="file" id="file" name="file" class="form-control d-none" value="${postFiles}" multiple>
				
				<div class="d-flex">
				<div class="fileUpdate form-control">
					<button type="button" class="fileSelect"> 파일 선택</button>
					<c:if test="${not empty postFiles}">
						<c:forEach items="${postFiles}" var="file">
							<c:set var="filePrint" value="${fn:split(file.filePath, '/')}" />
							${filePrint[fn:length(filePrint)-1]} <br>
						</c:forEach>
					</c:if>
				</div>
				</div>
			</div>
			<div class="buttonLine inputpage">
				<c:if test="${not empty postFiles}">
					<button type="button" class="postDeleteBtn btn btn-danger"> 파일 삭제</button>
				</c:if>
				<button type="submit" class="postRegistBtn btn btn-success" >수정</button>
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
		
		// 첨부파일 클릭 이벤트
		$('.fileUpdate').on('click', function() {
			$('#file').click();
		});
		
		// 첨부파일 change시 이벤트
		$('#file').on('change', function() {
			$(this).removeClass('d-none');
			$('.fileUpdate').text('');
			$('.fileUpdate').addClass('d-none');
			$('.postDeleteBtn').removeClass('d-none');
		});
		
		// 파일 삭제
		$('.postDeleteBtn').on('click', function() {
			$('.fileUpdate').text('');
			$('.fileUpdate').addClass('d-none');
			$('#file').val('');
			$('#file').removeClass('d-none');
			$(this).addClass('d-none');
		});
		
		// 공지사항 수정
		$('#postInsertForm').submit(function(e) {
			e.preventDefault();

			let title = $('#title').val();
			let content = $('#content').val();

			// 파일 Data
			let files = $('#file')[0].files;
			let filesArr = Array.prototype.slice.call(files);
			let formData = new FormData();
			formData.append('title', title);
			formData.append('content', content);
			
			for (let i = 0; i < filesArr.length; i++) {
				formData.append('filesArr', filesArr[i]);
			}
			
			let filePath;
			// 불러온 파일 경로
			if ($('.fileUpdate').text() == '') {
				filePath = false;
			} else {
				filePath = true;
			}
			formData.append('filePath', filePath);
			
			// 해당 게시물 번호
			formData.append('id', '${post.id}');
			
			$.ajax({
				type: 'PUT'
				, url: '/post/update'
				, data: formData
				, enctype : 'multipart/form-data'
				, processData : false
				, contentType : false
				, success:function(data) {
					if (data.result == 'success') {
						alert('공지사항 수정 완료');
						location.href='/post/post_list_view';
					}
				}
				, error:function(e) {
					alert('공지사항 수정 에러발생 : ' + e);
				}
			});
		});
	});
</script>