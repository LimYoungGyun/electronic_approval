<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/static/css/form.css">
<div class="page-content-size">
	<div class="contents box">
		<div class="content">
			<table class="table table-bordered detailTable">
				<tr>
					<th>이름</th>
					<td>${userName}</td>
					<th>상태</th>
					<td>${form.status}</td>
				</tr>
				<tr>
					<th>결재자</th>
					<td>${sendUserName}</td>
					<th>사용 연차 수</th>
					<td>${form.count}</td>
				</tr>
				<tr>
					<th>시작 일자</th>
					<td>${form.startDate}</td>
					<th>종료 일자</th>
					<td>${form.endDate}</td>
				</tr>
			</table>
<%-- 			<c:if test="${form.status ne '반려'}"> --%>
				<textarea id="content" name="content" class="form-control" rows="12" disabled>${form.content}</textarea>
<%-- 			</c:if> --%>
			<c:if test="${form.status eq '반려'}">
				<label for="reContent">반려 사유</label>
				<textarea id="reContent" name="reContent" class="form-control" rows="5" disabled>${form.reContent}</textarea>
			</c:if>
		</div>
		<div class="allButtonLine">
			<div class="delete">
<%-- 				<c:if test="${authorityForm == 'A'}"> --%>
<!-- 					<button type="button" class="groupDeleteBtn btn btn-danger">삭제</button> -->
<%-- 				</c:if> --%>
			</div>
			<div class="buttonLine inputpage">
				<button type="button" class="formListBtn btn btn-secondary">목록</button>
				<c:if test="${form.sendTo eq employeeId}">
					<c:if test="${form.status eq '결재 요청'}">
<%-- 						<button type="button" class="formDisapprovalBtn btn btn-danger" data-form-id="${form.id}">반려</button> --%>
						<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#disapprovalModal">반려</button>
						<button type="button" class="formApprovalBtn btn btn-success" data-form-id="${form.id}">승인</button>
					</c:if>
				</c:if>
				<c:if test="${form.status eq '반려' && form.employeeId eq employeeId}">
					<button type="button" class="formUpdateBtn btn btn-success" data-form-id="${form.id}">수정</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="disapprovalModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">반려 내용을 입력해 주세요.</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<textarea id="reContent" class="reContent form-control" rows="8" cols="" required></textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="formReContentBtn btn btn-secondary" data-dismiss="modal">취소</button>
				<button type="button" class="formDisapprovalBtn btn btn-danger" data-form-id="${form.id}">반려</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('기안서 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Form').addClass('active');
		
		// 게시물 삭제
// 		$('.groupDeleteBtn').on('click', function() {
// 			let check = confirm('해당 게시물을 삭제하시겠습니까??');
// 			if (check) {
// 				$.ajax({
// 					type: 'DELETE'
// 					, url: '/group/delete'
// 					, data: {
// 						'id' : ${group.id}
// 						, 'level' : ${group.level}
// 					}
// 					, success:function(data) {
// 						if (data.result == 'success') {
// 							alert('그룹 삭제 완료');
// 							location.href='/group/group_list_view';
// 						}
// 						if (data.result == 'group') {
// 							alert('하위 그룹이 존재합니다. 하위 그룹을 삭제 후 진행해 주세요.')
// 						}
// 					}
// 					, error:function(e) {
// 						alert('그룹 삭제 에러발생 : ' + e);
// 					}
// 				});
// 			}
// 		});
		
		// 그룹관리 리스트 페이지로 이동.
		$('.formListBtn').on('click', function() {
			location.href='/form/form_list_view';
		});
		
		// 기안서 반려
		$('.formDisapprovalBtn').on('click', function() {
			let formId = $(this).data('form-id');
			let reContent = $('.reContent').val();
			
			$.ajax({
				type : 'PUT'
				, url : '/form/disapproval'
				, data : {
					'formId':formId
					, 'reContent' : reContent
				}
				, success : function(data) {
					if (data.result == 'success') {
						alert('해당 기안서가 반려되었습니다.');
						location.href="/form/form_list_view";
					} else {
						alert('해당 기안서 반려가 실패하였습니다.');
					}
				}
				, error : function(e) {
					alert('반려 에러 : ' + e);
				}
			});
			
		});
		
		// 기안서 승인
		$('.formApprovalBtn').on('click', function() {
			let formId = $(this).data('form-id');
			let emplyoeeId = ${form.employeeId};
			let count = ${form.count};
			
			$.ajax({
				type : 'PUT'
				, url : '/form/approval'
				, data : {
					'id' : formId
					, 'emplyoeeId' : emplyoeeId
					, 'count' : count
				}
				, success :function(data) {
					if (data.result == 'success') {
						alert('해당 기안서가 승인되었습니다.');
						location.href="/form/form_list_view";
					} else {
						alert('해당 기안서 승인이 실패하였습니다.');
					}
				}
				, error : function(e) {
					alert('승인 에러 : ' + e);
				}
			});
		});
		
		$('.formReContentBtn').on('click', function() {
			$('#reContent').val('');
		});
		
		// 그룹관리 수정 페이지로 이동.
		$('.formUpdateBtn').on('click', function() {
			let formId = $(this).data('form-id');
			location.href='/form/form_update_view?formId='+ formId;
		});
	});
</script>