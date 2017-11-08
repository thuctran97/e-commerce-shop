<%@ page pageEncoding="utf-8"%>
<% 
	String view = request.getParameter("view");
	if (view.startsWith("user/")) {
		pageContext.include("user_layout.jsp");
	} else if (view.startsWith("admin/")) {
		pageContext.include("admin_layout.jsp");
	} else if (view.startsWith("home/")) {
		pageContext.include("home_layout.jsp");
	} else pageContext.include("blank_layout.jsp");
%>

<!-- không được gửi về client trước khi forward -->
<!-- nếu đã gửi chỉ có thể include -->
<!-- http://localhost:9999/EShop/account/edit.php -->
<!-- url -->
<!-- EShop/account/edit.php -->
<!-- uri -->
<!-- EShop  -->
<!-- contextpath -->