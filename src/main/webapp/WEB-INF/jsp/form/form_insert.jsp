<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/static/css/form.css">
<script src="/static/js/common.js"></script>
<div class="page-content-size">
	<div class="contents box">
		<form id="formInsertForm" action="/form/insert" method="POST">
			<div class="content">
				<div class="inputcontent">
					<div class="form-left">
						<label for="name">이름</label>
						<input type="text" id="name" name="name" class="form-control" value="${employeeName}" autocomplete="off" disabled>
					</div>
					<div class="form-right">
						<label for="remainAnnualLeave">남은 연차 수</label>
						<input type="text" id="remainAnnualLeave" name="remainAnnualLeave" class="remainAnnualLeave form-control" value="${remainAnnualLeave}" disabled>
					</div>
				</div>
				<div class="inputcontent">
					<div class="form-left">
						<label for="sendTo">발신자명</label>
						<select id="sendTo" name="sendTo" class="sendTo form-control" required>
							<option value="">-- 선택해 주세요 --</option>
							<c:forEach items="${annualIncome}" var="sendTo">
								<option value="${sendTo.id}">${sendTo.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-right">
						<label for="count">사용 연차 수</label>
						<input type="text" id="count" name="count" class="count form-control" value="0" disabled>
					</div>
				</div>
				<div class="inputcontent">
					<div class="form-left">
						<label for="useAnnualLeave">시작 일자</label>
						<input type="text" id="startDate" name="startDate" class="startDate form-control date" autocomplete="off" required>
					</div>
					<div class="form-right">
						<label for="remainAnnualLeave">종료 일자</label>
						<input type="text" id="endDate" name="endDate" class="endDate form-control date" autocomplete="off" required>
					</div>
				</div>
				<div class="inputcontent">
					<div class="form-left">
						<label for="content">내용</label>
						<textarea rows="8" cols="" id="content" name="content" class="content form-control"></textarea>
					</div>
				</div>
			</div>
			<div class="buttonLine inputpage">
				<button type="button" class="formListBtn btn btn-secondary">목록</button>
				<button type="submit" class="formRegistViewBtn btn btn-success" data-employee-id="${employeeId}">등록</button>
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
			, beforeShowDay: function(date){
				let day = date.getDay();
				return [(day != 0 && day != 6)];
			}
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
		
		// 기안 날짜 수를 구한다. (주말 제외)
		function countValue() {
			let start = $('#startDate').datepicker('getDate');
			let end = $('#endDate').datepicker('getDate');
			
			let dateStart = (start)/1000/60/60/24; // 계산을 위한 시작 날짜
			let dateEnd = (end)/1000/60/60/24; // 계산을 위한 마지막 날짜
			
			let count = 0;
			for (let i = dateStart; i <= dateEnd; i++) {
				let day = new Date(i*1000*60*60*24).getDay();
				if (day == 0 || day == 6) {
					continue;
				} else {
					count++;
				}
			}
			
// 			let days   = (end - start)/1000/60/60/24;
// 			$('#count').val(days + 1);
			$('#count').val(count);
		}
		
		$('#startDate').on('input', function() {
			$(this).val('');
		});
		$('#endDate').on('input', function() {
			$(this).val('');
		});

		// 기안서 관리 리스트 페이지로 이동.
		$('.formListBtn').on('click', function() {
			location.href='/form/form_list_view';
		});
		
		// 등록
		let data = $('#formInsertForm').serialize();
		$('#formInsertForm').submit(function(e) {
			e.preventDefault();

			let employeeId = $('.formRegistViewBtn').data('employee-id'); // 등록자 ID
			let sendTo = $('#sendTo').val(); // 발신자 직원 Id
			let count = $('#count').val(); // 사용 연차수
			let startDate = $('#startDate').val(); // 시작 일자
			let endDate = $('#endDate').val(); // 종료 일자
			let content = $('#content').val(); // 내용
			
			$.ajax({
				type:'POST'
				, url : '/form/insert'
				, data : {
					'employeeId' : employeeId // 게시물 작성자 employeeId
					, 'sendTo' : sendTo // 발신자 직원 Id
					, 'count' : count // 연차 개수
					, 'startDate' : startDate // 연차 시작 일자
					, 'endDate' : endDate // 연차 종료 일자
					, 'content' : content // 연차 시 입력 내용
				}
				, success : function(data) {
					if (data.result == 'success') {
						alert('기안서 등록이 완료되었습니다.');
						location.href="/form/form_list_view";
					} else {
						alert('기안서 등록에 실패하였습니다.');
					}
				}
				, error : function(e) {
					alert('기안서 등록 에러발생 : ' + e);
				}
			});
		});
	});
</script>
