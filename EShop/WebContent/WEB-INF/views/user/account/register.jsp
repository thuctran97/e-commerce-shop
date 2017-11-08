<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Registration</h2>
	${message}
	<form:form action="account/register.php" modelAttribute="user" enctype="multipart/data">
<!-- 	enctype="multipart/data" cho file upload  -->
		<div class=form-group>
			<label>UserName</label>
			<form:input path="id" cssClass="form-control"/>
		</div>
		<div class=form-group>
			<label>Password</label>
			<form:input path="password" cssClass="form-control"/>
		</div>
		<div class=form-group>
			<label>Confirm Password</label>
			<input name="password1" class="form-control"/> 
		</div>
		<div class=form-group>
			<label>Fullname</label>
			<form:input path="fullname" cssClass="form-control"/>
		</div>
		<div class=form-group>
			<label>Email Address</label>
			<form:input path="email" cssClass="form-control"/>
		</div>
		<div class=form-group>
			<label>Photo</label>
			<input name="upphoto" type="file" cssClass="form-control" />
		</div>
		<form:hidden path="activated"/>
		<div class="form-group">
			<button class="btn btn-default">
				<span class="glyphicon glyphicon-user"></span> REGISTER
			</button>
		</div>
	</form:form>
</body>
</html>