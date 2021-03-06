<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="logo-details">
	<a href="/home/home_list_view"><i class='bx bxl-c-plus-plus'></i> <span class="logo_name">el_approval</span></a>
</div>
<ul class="nav-links">
	<li>
		<a href="/post/post_list_view" class="links_name_Dashboard">
			<i class='bx bx-folder-open'></i> <span class="links_name">Dashbord</span>
		</a>
	</li>
	<li>
		<a href="/group/group_list_view" class="links_name_Group">
			<i class='bx bx-user-pin'></i> <span class="links_name">Group</span>
		</a>
	</li>
	<li>
		<a href="/employee/employee_list_view"  class="links_name_Employee">
			<i class='bx bx-user-check'></i> <span class="links_name">Employee</span>
		</a>
	</li>
	<li>
		<a href="/commute/commute_list_view"  class="links_name_Commute">
			<i class='bx bx-bell-minus'></i> <span class="links_name">Commute</span>
		</a>
	</li>
	<li>
		<a href="/form/form_list_view"  class="links_name_Form">
		<i class='bx bx-receipt'></i> <span class="links_name">Form</span>
		</a>
	</li>
</ul>
<div class="profile_content">
	<div class="profile">
		<div class="profile_details">
			<img src="${profilePath}" alt="">
			<div class="name_organization">
				<div class="name">${employeeName}</div>
				<div class="organization"><${employeePosition}> ${employeeGroup}</div>
			</div>
		</div>
		<a href="/user/sign_out"><i class='bx bx-log-out' id="log_out"></i></a>
	</div>
</div>