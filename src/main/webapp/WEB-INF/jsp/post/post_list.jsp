<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/static/css/post.css">
<div class="page-content-size">
	<div class="contents box">
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
					<c:if test="${empty postListPage}">
						<tr>
							<td colspan="4">조회된 그룹 정보가 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty postListPage}">
						<c:forEach items="${postListPage}" var="post" varStatus="status">
							<tr>
								<td>${paging.totalArticle - ((paging.page - 1) * paging.pageSize) - status.index}</td>
								<td class="text-left">${post.title}</td>
								<td>${emoployeeList.get(status.index)}</td>
								<td><fmt:formatDate value="${post.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss" var="updatedAt"/>${updatedAt}</td>
								<td class="d-none">${post.id}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			
		</div>
		<div class="buttonLine pageLine">
			<div></div>
			<div class="paging d-flex justify-content-center align-items-end">
				<c:if test="${pageMaker.prev ne false}">
					<a href="/post/post_list_view?page=${pageMaker.startPage - 1}" class="mr-5">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1">
					<a href="/post/post_list_view?page=${i}" id="pageNumber" class="mr-5 pageNumber page${i}" data-page-num="${i}">${i}</a>
				</c:forEach>
				<c:if test="${pageMaker.next ne false}">
					<a href="/post/post_list_view?page=${pageMaker.endPage + 1}">[다음]</a>
				</c:if>
			</div>
			<c:if test="${authorityPost == 'WR'}">
				<button type="button" class="postRegistViewBtn btn btn-success">등록</button>
			</c:if>
			<div class="space"></div>
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
		
		// 현재 페이지 번호 표시(bold)
		let pageNum = ${paging.page};
		$('.page' + pageNum).addClass('font-weight-bold');
		$('.page' + pageNum).css('font-size', '20px');
		$('.page' + pageNum).css('height', '27.5px');
		
		// page size 조절
		if (${authorityPost == 'WR'}) {
			$('.space').addClass('d-none');
		}
		
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
			
			location.href='/post/post_detail_view?postId='+postId;
		});
		
	});
</script>