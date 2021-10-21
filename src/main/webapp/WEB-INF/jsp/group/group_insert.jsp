<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/static/css/group.css">
<div class="page-content-size">
	<div class="contents box">
		<form id="groupInsertForm" action="/group/insert" method="POST">
			<div class="content">
				<label for="groupName">그룹명</label>
				<input type="text" id="groupName" name="groupName" class="form-control" autocomplete="off" required>
				
				<label for="topLevel">상위 그룹</label>
				<select id="topLevel" name="topLevel" class="form-control" required>
					<option value="">-- 상위 그룹을 선택하세요 -- </option>
					<%-- value에 DB column "id"을 넣어준다. --%>
					<c:forEach items="${groupList}" var="group">
						<option value="${group.id}">${group.groupName}</option>
					</c:forEach>
					<c:if test="${empty groupList}">
						<option value="-1">없음</option>
					</c:if>
				</select>
				
				<label for="content">업무 내용</label>
				<textarea id="content" name="content" class="form-control" rows="15" required></textarea>
				
			</div>
			<c:if test="${authorityGroup == 'WR'}">
				<div class="buttonLine inputpage">
					<button type="submit" class="groupRegistBtn btn btn-success">등록</button>
				</div>
			</c:if>
		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('그룹 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Group').addClass('active');
		
		// 그룹 등록
		$('#groupInsertForm').submit(function(e) {
			e.preventDefault();
			
			let groupName = $('#groupName').val();
			let topLevel = $('#topLevel').val();
			let content = $('#content').val();
			
			$.ajax({
				type: 'POST'
				, url: '/group/insert'
				, data: {
					'groupName':groupName
					, 'topLevelId':topLevel
					, 'content':content
				}
				, success:function(data) {
					if (data.result == 'success') {
						alert('그룹 등록 완료');
						location.href='/group/group_list_view';
					}
				}
				, error:function(e) {
					alert('그룹 등록 에러발생 : ' + e);
				}
			});
		});
	});
</script>
