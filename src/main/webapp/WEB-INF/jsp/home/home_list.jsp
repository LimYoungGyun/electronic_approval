<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <div class="sales-boxes"> -->
<div class="test">
	<div class="contents box">
		<div class="title">공지사항</div>
		<div class="content">
			<table class="table">
				<tr>
					<th>1</th>
					<th>2</th>
					<th>3</th>
					<th>4</th>
				</tr>
				<tr>
					<td>adf</td>
					<td>adf</td>
					<td>asd</td>
					<td>asdf</td>
				</tr>
			</table>
		</div>
	</div>
	<br>
	<div class="contents box">
		<div class="title">휴가자 목록</div>
		<div class="content">
			<table class="table">
				<tr>
					<th>1</th>
					<th>2</th>
					<th>3</th>
					<th>4</th>
				</tr>
				<tr>
					<td>adf</td>
					<td>adf</td>
					<td>asd</td>
					<td>asdf</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<div class="top-sales box">
	<div class="title">출 퇴근 현황</div>
	<table class="table">
		<tr>
			<th>1</th>
			<th>2</th>
			<th>3</th>
			<th>4</th>
		</tr>
		<tr>
			<td>adf</td>
			<td>adf</td>
			<td>asd</td>
			<td>asdf</td>
		</tr>
	</table>
</div>
<script>
	$(document).ready(function() {
		// titleName
		$('.dashboard').text('전자결재시스템');
		if ($('.nav-links a').hasClass('active')) {
			$('a').removeClass('active');
		}
	});
</script>
