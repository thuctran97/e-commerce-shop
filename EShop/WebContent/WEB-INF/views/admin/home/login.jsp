<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
</head>
<body>
	<h1>Login</h1>
	${message}
	<form action="admin/home/login.php" method="post">
		
		<div class="form-group">
			<label>Username</label>
			<input name="id" class="form-control">
		</div>
		
		<div class="form-group">
			<label>Password</label>
			<input name="password" class="form-control">
		</div>
		
		<div class="form-group">
			<button class="btn btn-default">Login</button>
		</div>
	</form>
</body>
</html>