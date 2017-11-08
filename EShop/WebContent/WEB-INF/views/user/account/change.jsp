<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Change Password</h2>
	${message}
	<form action="account/change.php" method="post">
		<div class=form-group>
			<label>UserName</label>
			<input name="id" class="form-control">
		</div>
		<div class=form-group>
			<label>Current Password</label>
			<input name="password" class="form-control" >
		</div>
		<div class=form-group>
			<label>New Password</label>
			<input name="password1" class="form-control" >
		</div>
		<div class=form-group>
			<label>Confirm New Password</label>
			<input name="password2" class="form-control" >
		</div>
		<div class="form-group">
			<button class="btn btn-default">
				<span class="glyphicon glyphicon-save"></span> CHANGE PASSWORD
			</button>
		</div>
	</form>
</body>
</html>