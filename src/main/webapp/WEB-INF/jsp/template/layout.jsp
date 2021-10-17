<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		
		<!-- datepicker 라이브러리 -->
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		
		<link rel="stylesheet" href="/static/css/layout.css">
		
		<!-- Boxicons CDN Link -->
		<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
		
		<title>전자결재시스템</title>
	</head>

	<body>
		<!-- left menu -->
		<div class="sidebar">
			<jsp:include page="../include/gnb.jsp"></jsp:include>
		</div>
		
		<section class="home-section">
			<!-- left menu -->
			<nav>
				<jsp:include page="../include/top.jsp"></jsp:include>
			</nav>
			
			<!-- main content -->
			<div class="home-content">
				<div class="main-content">
					<jsp:include page="../${viewName}.jsp"></jsp:include>
				</div>
			</div>
		</section>

	<script>
		// let sidebar = document.querySelector(".sidebar");
		// let sidebarBtn = document.querySelector(".sidebarBtn");
		// sidebarBtn.onclick = function () {
		//     sidebar.classList.toggle("active");
		//     if (sidebar.classList.contains("active")) {
		//         sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
		//     } else
		//         sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
		// }

		$('.sidebarBtn').on('click', function() {
			$('.sidebar').toggleClass('active');
			if ($('.sidebar').hasClass('active')) {
				$(this).removeClass('bx-menu');
				$(this).addClass('bx-menu-alt-right');
			} else {
				$(this).removeClass('bx-menu-alt-right');
				$(this).addClass('bx-menu');
			}
		});
	</script>

</body>

</html>