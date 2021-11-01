<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/static/css/form.css">
<script src="/static/js/common.js"></script>
<div class="page-content-size">
	<div class="contents box">
		<form id="formInsertForm" action="">
			<div class="content">
				<div class="inputcontent">
					<div class="form-left">
						<label for="name">이름</label>
						<input type="text" id="name" name="name" class="form-control" value="${employeeName}" autocomplete="off" disabled>
					</div>
					<div class="form-right">
						<label for="remainAnnualLeave">남은 연차 수</label>
						<input type="text" id="remainAnnualLeave" name="remainAnnualLeave" class="form-control" value="${remainAnnualLeave}" disabled>
					</div>
				</div>
				<div class="inputcontent">
					<div class="form-left">
						<label for="">발신자명</label>
						<select id="annualIncome" class="form-control">
							<option>test</option>
						</select>
<!-- 						<input type="text" id="annualIncome" name="annualIncome" class="form-control" autocomplete="off" required> -->
					</div>
					<div class="form-right">
						<label for="count">사용 연차 수</label>
						<input type="text" id="count" name="count" class="form-control" value="0" disabled>
					</div>
				</div>
				<div class="inputcontent">
					<div class="form-left">
						<label for="useAnnualLeave">시작 일자</label>
						<input type="text" id="startDate" name="startDate" class="form-control date" autocomplete="off" readonly required>
					</div>
					<div class="form-right">
						<label for="remainAnnualLeave">종료 일자</label>
						<input type="text" id="endDate" name="endDate" class="form-control date" autocomplete="off" readonly required>
					</div>
				</div>
				<div class="inputcontent">
					<div class="form-left">
						<label for="">내용</label>
						<textarea rows="8" cols="" class="form-control"></textarea>
					</div>
				</div>
			</div>
			<div class="buttonLine inputpage">
				<button type="button" class="formListBtn btn btn-secondary">목록</button>
				<button type="submit" class="formUpdateBtn btn btn-success">등록</button>
			</div>
		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		// top menu setting
		$('.dashboard').text('기안서 관리');
		
		// left menu setting
		$('.nav-links a').removeClass('active');
		$('.links_name_Form').addClass('active');
		
		$.datepicker.setDefaults({
			dateFormat: 'yy-mm-dd'
			, prevText: '이전 달'
			, nextText: '다음 달'
			, showButtonPanel: true
			, currentText: "오늘"
			, closeText: "닫기"
			, monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
			, dayNamesMin: ['일', '월', '화', '수', '목', '금', '토']
			, showMonthAfterYear: true
			, yearSuffix: '년'
			, minDate: 0
		});
		$.datepicker._gotoToday = function(id) {
			$(id).datepicker('setDate', new Date()).datepicker('hide').blur();
		};
		
		// 데이터 가공
		$("#startDate").datepicker({
			onSelect:function(dateText) {
				$('#endDate').datepicker('option', 'minDate', dateText);
				if ($('#endDate').val() != '') {
					countValue();
				}
			}
		});
		$("#endDate").datepicker({ });
		
		$('#endDate').on('change', function() {
			if ($('#startDate').val() != '') {
				countValue();
			}
		});
		
		function countValue() {
			let start = $('#startDate').datepicker('getDate');
			let end   = $('#endDate').datepicker('getDate');
			let days   = (end - start)/1000/60/60/24;
			$('#count').val(days + 1);
		}
		
		// 기안서 관리 리스트 페이지로 이동.
		$('.formListBtn').on('click', function() {
			location.href='/form/form_list_view';
		});
		
	});
</script>
