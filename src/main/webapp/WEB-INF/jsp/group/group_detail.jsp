<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/static/css/group.css">
<div class="page-content-size">
	<div class="contents box">
		<div class="content">
			<table class="table table-bordered detailTable">
				<tr>
					<th>상위 그룹</th>
					<td colspan="4">${group.topGroupName }</td>
				</tr>
				<tr>
					<th>그룹명</th>
					<td>${group.groupName}</td>
					<th>등록일</th>
					<td><fmt:formatDate value="${group.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss" var="updatedAt"/>${updatedAt}</td>
				</tr>
			</table>
			<textarea id="content" name="content" class="form-control" rows="15" disabled>${group.content}</textarea>
		</div>
		<div class="allButtonLine">
			<div class="delete">
				<c:if test="${authorityGroup == 'WR'}">
					<button type="button" class="groupDeleteBtn btn btn-danger">삭제</button>
				</c:if>
			</div>
			<div class="buttonLine inputpage">
				<button type="button" class="groupListBtn btn btn-secondary">목록</button>
				<c:if test="${authorityGroup == 'WR'}">
					<button type="button" class="groupDetailBtn btn btn-success">수정</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('그룹 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Group').addClass('active');
		
		// 게시물 삭제
		$('.groupDeleteBtn').on('click', function() {
			let check = confirm('해당 게시물을 삭제하시겠습니까??');
			if (check) {
				$.ajax({
					type: 'DELETE'
					, url: '/group/delete'
					, data: {
						'id' : ${group.id}
						, 'level' : ${group.level}
					}
					, success:function(data) {
						if (data.result == 'success') {
							alert('그룹 삭제 완료');
							location.href='/group/group_list_view';
						}
						if (data.result == 'group') {
							alert('하위 그룹이 존재합니다. 하위 그룹을 삭제 후 진행해 주세요.')
						}
					}
					, error:function(e) {
						alert('그룹 삭제 에러발생 : ' + e);
					}
				});
			}
		});
		
		// 그룹관리 리스트 페이지로 이동.
		$('.groupListBtn').on('click', function() {
			location.href='/group/group_list_view';
		});
		
		// 그룹관리 수정 페이지로 이동.
		$('.groupDetailBtn').on('click', function() {
			location.href='/group/group_update_view?groupId='+ ${group.id};
		});
	});
</script>