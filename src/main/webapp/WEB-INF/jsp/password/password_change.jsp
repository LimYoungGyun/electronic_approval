<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="/static/css/sign_in.css">
		
		<title>password change</title>
	</head>
<body>
	<div class="center">
		<h1>password change</h1>
		<form id="password-change-form" action="" method="POST">
			<div class="txt_field">
				<input type="password" id="password" class="password" autocomplete="off" required> <span></span> <label>password </label>
			</div>
			<div class="txt_field">
				<input type="password" id="passwordCheck" class="passwordCheck" autocomplete="off" required> <span></span> <label>password check</label>
			</div>
			<button type="submit" class="sign-in-btn">confirm</button>
		</form>
	</div>
</body>
<script>
	$(document).ready(function() {
		$('#password-change-form').submit(function(e) {
			e.preventDefault();
			
			let password = $('.password').val();
			let passwordCheck = $('.passwordCheck').val();
			
			if (password != passwordCheck) {
				alert('비밀번호가 일치하지 않습니다.');
				return;
			}
			
			$.ajax({
				type : 'PUT'
				, url : '/password/password_change'
				, data : {'password' : password}
				, success : function(data) {
					if (data.result == 'success') {
						alert('비밀번호가 변경되었습니다. \n로그인해 주세요.');
						location.href = '/user/sign_in_view';
					} else {
						alert('비밀번호 변경이 실패하였습니다.');
					}
				}
				, error : function(e) {
					alert('비밀번호 변경 에러' + e);
				}
			})
		});
	});
</script>
</html>