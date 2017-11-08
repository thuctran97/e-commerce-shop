<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<style type="text/css">
	.nndetail img{
		width: 50px;
		height: 50px;
		margin: 3px;
		padding: 5px;
		box-shadow: 0 0 5px blue;
		border-radius: 5px;
	}
	.nndetail img:hover {
		box-shadow: 0 0 5px red;
	}
</style>
</head>
<body>
	<h1>PRODUCT DETAIL</h1>
	<div class="row">
		<img class="col-sm-5" src="images/products/${prod.image}">
		<ul class="col-sm-7">
			<li>Name: ${prod.name}</li>
			<li>Unit Price: ${prod.unitPrice}</li>
			<li>Unit Brief: ${prod.unitBrief}</li>
			<li>Product Date: ${prod.productDate}</li>
			<li>Discount: ${prod.discount}</li>
			<li>Category: ${prod.category.nameVN}</li>
			<li>Supplier: ${prod.supplier.name}</li>
		</ul>
	</div>
	<div class="row">
		<div class="col-sm-12">${prod.description}</div>
	</div>
	<div class="row nndetail">
		<fieldset class="col-sm-12">
			<legend>SAME CATEGORY ITEMS</legend>
			<c:forEach var="p" items="${sameCateItems}">
				<a href="product/detail/${p.id}.php"> <img
					src="images/products/${p.image}"> </a>
			</c:forEach>
		</fieldset>

		<fieldset class="col-sm-12">
			<legend>SAME SUPPLIER ITEMS</legend>
			<c:forEach var="p" items="${sameSuppItems}">
				<a href="product/detail/${p.id}.php"> <img
					src="images/products/${p.image}"> </a>
			</c:forEach>
		</fieldset>
	</div>
</body>
</html>
