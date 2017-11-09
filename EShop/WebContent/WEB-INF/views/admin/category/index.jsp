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
	<h1>Category Manager</h1>

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
	    ${message} ${param.message}
<!-- 	   	message trong attribute -->
<!-- 	   	param.message trong param 	 -->
		<form:form action="admin/category/index.php" modelAttribute="item">
			<div class="form-group">
				<label>Id</label>
				<form:input path="id" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<label>Name</label>
				<form:input path="name" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<label>NameVN</label>
				<form:input path="nameVN" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<button class="btn btn-primary" formaction="admin/category/insert.php">
					<span class="glyphicon glyphicon-plus"></span> Insert
				</button>
				<button class="btn btn-warning" formaction="admin/category/update.php">
					<span class="glyphicon glyphicon-save"></span> Update
				</button>
				<button class="btn btn-danger" formaction="admin/category/delete.php">
					<span class="glyphicon glyphicon-trash"></span> Delete
				</button>
				<button class="btn btn-info" formaction="admin/category/index.php">
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
						<th>Name VN</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o" items="${items}">
<!-- 					@ModelAttribute("items") -->
<!-- 	public List<Category> getCategoryList() { -->
<!-- 		return categoryService.list(); -->
<!-- 	} -->
					
					<tr>
						<td>${o.id}</td>
						<td>${o.name}</td>
						<td>${o.nameVN}</td>
						<td>
							<a href="admin/category/edit/${o.id}.php" class="btn btn-sm btn-default">
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