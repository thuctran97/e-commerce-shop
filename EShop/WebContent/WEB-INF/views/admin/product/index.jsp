<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
	<script src="js/product-list.js"></script>
</head>
<body>
	<h1>Product Manager</h1>

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
		<form:form action="admin/product/index.php" modelAttribute="item" 
			enctype="multipart/form-data">
			<div class="row">
				<div class="form-group col-sm-4">
					<label>Id</label>
					<form:input path="id" cssClass="form-control"/>
				</div>
				<div class="form-group col-sm-4">
					<label>Name</label>
					<form:input path="name" cssClass="form-control"/>
				</div>
				<div class="form-group col-sm-4">
					<label>UnitPrice</label>
					<form:input path="unitPrice" cssClass="form-control"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-4">
					<label>Discount</label>
					<form:input path="discount" cssClass="form-control"/>
				</div>
				<div class="form-group col-sm-4">
					<label>Image</label>
					<input type="file" name="upimage" class="form-control">
					<form:hidden path="image"/>
				</div>
				<div class="form-group col-sm-4">
					<label>Quantity</label>
					<form:input path="quantity" cssClass="form-control"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-4">
					<label>Unit Brief</label>
					<form:input path="unitBrief" cssClass="form-control"/>
				</div>
				<div class="form-group col-sm-4">
					<label>Product Date</label>
					<form:input path="productDate" cssClass="form-control"/>
				</div>
				<div class="form-group col-sm-4">
					<label>View Count</label>
					<form:input path="views" cssClass="form-control"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-4">
					<label>Available?</label>
					<div class="form-control">
						<form:radiobutton path="available"  value="true"  label="Yes"/>
						<form:radiobutton path="available"  value="false"  label="No"/>
					</div>
				</div>
				<div class="form-group col-sm-4">
					<label>Special?</label>
					<div class="form-control">
						<form:radiobutton path="special"  value="true"  label="Yes"/>
						<form:radiobutton path="special"  value="false"  label="No"/>
					</div>
				</div>
				<div class="form-group col-sm-4">
					<label>Latest?</label>
					<div class="form-control">
						<form:radiobutton path="latest"  value="true"  label="Yes"/>
						<form:radiobutton path="latest"  value="false"  label="No"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-4">
					<label>Category</label>
					<form:select path="category.id" cssClass="form-control" 
						items="${citems}" itemValue="id" itemLabel="nameVN"/>
				</div>
				<div class="form-group col-sm-4">
					<label>Supplier</label>
					<form:select path="supplier.id" cssClass="form-control" 
						items="${sitems}" itemValue="id" itemLabel="name"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label>Description</label>
					<form:textarea path="description" cssClass="form-control" rows="4"/>
				</div>
				<div class="form-group col-sm-12">
					<button class="btn btn-primary" formaction="admin/product/insert.php">
						<span class="glyphicon glyphicon-plus"></span> Insert
					</button>
					<button class="btn btn-warning" formaction="admin/product/update.php">
						<span class="glyphicon glyphicon-save"></span> Update
					</button>
					<button class="btn btn-danger" formaction="admin/product/delete.php">
						<span class="glyphicon glyphicon-trash"></span> Delete
					</button>
					<button class="btn btn-info" formaction="admin/product/index.php">
						<span class="glyphicon glyphicon-refresh"></span> Reset
					</button>
				</div>
			</div>
		</form:form>
	  </div>
	<div id="gridview" class="tab-pane fade">
	    <!-- TABLE -->
	    <ul class="pager">
	    	<li><a href="#"><span class="glyphicon glyphicon-hand-up"></span></a></li> 
<!-- 	    	0 -->
	    	<li><a href="#"><span class="glyphicon glyphicon-hand-left"></span></a></li>
<!-- 	    	1 -->
	    	<li><a href="#">1/22</a></li>
<!-- 	    	2 -->
	    	<li><a href="#"><span class="glyphicon glyphicon-hand-right"></span></a></li>
<!-- 	    	3 -->
	    	<li><a href="#"><span class="glyphicon glyphicon-hand-down"></span></a></li>
<!-- 	    	4 -->
	    </ul>
	    <div id="page"></div>
	  </div>
	</div>
	
</body>
</html>
