<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
		
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
<!-- 		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script> -->
<!-- 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> -->
		
		<!-- datepicker 라이브러리 -->
<!-- 		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<!-- 		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
		
		<link rel="stylesheet" href="/static/css/sign_in.css">
		
		<title>sign in</title>
	</head>
<body>
	<div class="center">
		<h1>Login</h1>
		<form id="sign-form" action="/user/sign_in" method="POST">
			<div class="txt_field">
				<input type="text" id="email" class="email" autocomplete="off"
					required> <span></span> <label>email</label>
			</div>
			<div class="txt_field">
				<input type="password" id="password" class="password"
					autocomplete="off" required> <span></span> <label>password</label>
			</div>
			<button type="submit" class="sign-in-btn">sign in</button>
		</form>
	</div>
</body>
<script>
	$(document).ready(function() {
		$('#sign-form').submit(function(e) {
			e.preventDefault();

			let email = $('#email').val().trim();
			let password = $('#password').val().trim();

			console.log('email : ' + email);
			console.log('password : ' + password);

			$.ajax({
				type : 'POST',
				url : '/user/sign_in',
				data : {
					'email' : email,
					'password' : password
				},
				success : function(data) {
					if (data.result == 'success') {
						alert('login success');
						location.href = '/home/home_list_view';
					} else {
						alert('이메일 또는 비밀번호를 확인해주세요.');
					}
				},
				error : function(e) {
					alert('login fail' + e);
				}
			});
		});
	});
</script>
</html>