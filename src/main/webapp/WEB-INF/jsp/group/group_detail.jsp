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
			<div class="buttonLine inputpage">
				<button type="button" class="groupListBtn btn btn-secondary">목록으로</button>
				<c:if test="${authorityGroup == 'WR'}">
					<button type="button" class="groupDetailBtn btn btn-success">수정</button>
				</c:if>
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