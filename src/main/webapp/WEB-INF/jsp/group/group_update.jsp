<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <link rel="stylesheet" href="/static/css/common.css"> --%>
<link rel="stylesheet" href="/static/css/group.css">
<div class="page-content-size">
	<div class="contents box">
		<div class="content">
			<label for="groupName">그룹명</label>
			<input type="text" id="groupName" name="groupName" class="form-control" value="${group.groupName}" autocomplete="off" required>
			
			<label for="topLevel">상위 그룹</label>
			<select id="topLevel" name="topLevel" class="form-control" required>
				<option value="">-- 상위 그룹을 선택하세요 -- </option>
				<%-- value에 DB column "id"을 넣어준다. --%>
				<c:forEach items="${groupList}" var="groups">
					<c:if test="${groups.id ne group.id}">
						<option value="${groups.id}">${groups.groupName}</option>
					</c:if>
				</c:forEach>
				<c:if test="${empty groupList}">
					<option value="-1">없음</option>
				</c:if>
			</select>
			
			<label for="content">업무 내용</label>
			<textarea id="content" name="content" class="form-control" rows="15" required>${group.content}</textarea>
			
		</div>
			<div class="buttonLine inputpage">
				<button type="button" class="groupListBtn btn btn-secondary">목록</button>
				<c:if test="${authorityGroup == 'WR'}">
					<button type="button" class="groupUpdateBtn btn btn-success">수정</button>
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
		
		// select 값 선택
		if (${group.topLevel} == -1) {
			$('#topLevel').val(${group.id}).prop('selected');
			$('#topLevel').prop('disabled', true);
		}
		
		// 선택된 select값
		$('#topLevel').val(${group.topId}).prop('selected');
		
		// 그룹관리 리스트 페이지로 이동.
		$('.groupListBtn').on('click', function() {
			location.href='/group/group_list_view';
		});
		
		// 그룹관리 수정
		$('.groupUpdateBtn').on('click', function() {
			
			let groupName = $('#groupName').val();
			let topLevel = $('#topLevel option:selected').val();
			let content = $('#content').val();
			
			$.ajax({
				type: 'PUT'
				, url: '/group/update'
				, data: {
					'groupId':${group.id}
					, 'groupName':groupName
					, 'topLevelId':topLevel
					, 'content':content
				}
				, success:function(data) {
					if (data.result == 'success') {
						alert('그룹관리 수정 완료');
						location.href='/group/group_list_view';
					}
				}
				, error:function(e) {
					alert('그룹관리 수정 에러발생 : ' + e);
				}
			});
		});
	});
</script>