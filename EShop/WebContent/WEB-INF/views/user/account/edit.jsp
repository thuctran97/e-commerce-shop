<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Lap trinh Java</title>
</head>
<body>
	<h2>Edit Profile</h2>
	${message}
	<form:form action="account/edit.php" modelAttribute="user"
		enctype="multipart/form-data">
		<div class="form-group">
			<label>Username</label>
			<form:input path="id" cssClass="form-control" readonly="true" />
		</div>

		<form:hidden path="password" />

		<div class="form-group">
			<label>Fullname</label>
			<form:input path="fullname" cssClass="form-control" />
		</div>

		<div class="form-group">
			<label>Email Address</label>
			<form:input path="email" cssClass="form-control" />
		</div>

		<div class="form-group">
			<label>Photo</label> <input name="upphoto" type="file"
				cssClass="form-control" />
			<form:hidden path="photo" />
			<img src="images/customers/${user.photo}" style="width: 100px;">
		</div>

		<form:hidden path="activated" />

		<div class="form-group">
			<button class="btn btn-default">
				<span class="glyphicon glyphicon-saved"></span> UPDATE
			</button>
		</div>
	</form:form>
</body>
</html>