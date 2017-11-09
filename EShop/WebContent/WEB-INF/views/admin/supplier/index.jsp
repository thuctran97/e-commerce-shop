<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
</head>
<body>
	<h1>Supplier Manager</h1>

	<ul class="nav nav-tabs">
	  <li class="active">
	  	<a data-toggle="tab" href="#form">
	  		<span class="glyphicon glyphicon-edit"></span> Edition
	  	</a>
	  </li>
	  <li>
	  	<a data-toggle="tab" href="#gridview">
	  		<span class="glyphicon glyphicon-list"></span> List of Items
	  	</a>
	  </li>
	</ul>

	<div class="tab-content">
	  <div id="form" class="tab-pane fade in active">
	    <!-- FORM -->
	    ${message}${param.message}
		<form:form action="admin/supplier/index.php" modelAttribute="item"
		enctype="multipart/form-data">
<!-- 		x -->
			<div class="form-group">
				<label>Id</label>
				<form:input path="id" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<label>Name</label>
				<form:input path="name" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<label>Logo</label>
				<input type="file" name="uplogo" class="form-control"> 
<!-- 				x -->
				<form:hidden path="logo"/>
			</div>
			<div class="form-group">
				<label>Phone</label>
				<form:input path="phone" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<label>Email</label>
				<form:input path="email" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<button class="btn btn-primary" formaction="admin/supplier/insert.php">
					<span class="glyphicon glyphicon-plus"></span> Insert
				</button>
				<button class="btn btn-warning" formaction="admin/supplier/update.php">
					<span class="glyphicon glyphicon-save"></span> Update
				</button>
				<button class="btn btn-danger" formaction="admin/supplier/delete.php">
					<span class="glyphicon glyphicon-trash"></span> Delete
				</button>
				<button class="btn btn-info" formaction="admin/supplier/index.php">
					<span class="glyphicon glyphicon-refresh"></span> Reset
				</button>
			</div>
		</form:form>
	  </div>
	  <div id="gridview" class="tab-pane fade">
	    <!-- TABLE -->
	    	<table class="table">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Logo</th>
						<th>Phone</th>
						<th>Email</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o" items="${items}">
					<tr>
						<td>${o.id}</td>
						<td>${o.name}</td>
						<td>${o.logo}</td>
						<td>${o.phone}</td>
						<td>${o.email}</td>
						<td>
							<a href="admin/supplier/edit/${o.id}.php" class="btn btn-sm btn-default">
								<span class="glyphicon glyphicon-edit"></span> Edit
							</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
	  </div>
	</div>
</body>
</html>