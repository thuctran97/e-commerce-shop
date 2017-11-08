<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h2>LOGIN</h2>
	${message}
	<form action="account/login.php" method="post">
		<div class=form-group>
			<label>UserName</label>
			<input name="id" class="form-control" value="${cookie.uid.value }">
		</div>
		<div class=form-group>
			<label>Password</label>
			<input name="password" class="form-control" value="${cookie.pwd.value }">
		</div>
		<div class=form-group>
			<label>
				<input name="remember" type="checkbox">
				Remember me?
			</label>
		</div>
		<div class="form-group">
			<button class="btn btn-default">
				<span class="glyphicon glyphicon-log-in"></span> LOGIN
			</button>
		</div>
	</form>
</body>
</html>