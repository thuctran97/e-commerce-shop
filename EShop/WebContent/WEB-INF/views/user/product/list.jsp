<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
	<link href="css/product.css" rel="stylesheet">
	<script src="js/shopping-cart.js"></script>
	<style id="nn-cart-fly">
	
	
	</style>
</head>
<body>
	<h2>LIST O PRODUCTS</h2>
	<c:forEach var="p" items="${prods}">
	<div class="col-sm-4 nn-box">
		<div class="panel panel-default">
			<div class="panel-hedding">${p.name}</div>
			<div class="panel-body">
				<a href="product/detail/${p.id}.php">
					<img src="images/products/${p.image}">
				</a>
			</div>
			<div class="panel-footer">
				<div class="row">
					<div class="pull-left">${p.unitPrice}</div>
					<div class="pull-right">
						<button class="btn btn-sm btn-primary" data-cart-add="${p.id}">
							<span class="glyphicon glyphicon-shopping-cart"></span>
						</button>
						<button class="btn btn-sm btn-danger">
							<span class="glyphicon glyphicon-envelope"></span>
						</button>
						<button class="btn btn-sm btn-warning">
							<span class="glyphicon glyphicon-heart"></span>
						</button>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${p.special}">
					<img src="images/special_icon.gif">	
				</c:when>
				<c:when test="${p.latest}">
					<img src="images/new_icon.gif">	
				</c:when>
				<c:when test="${p.discount > 0}">
					<img src="images/promo_icon.gif">	
				</c:when>
			</c:choose>
		</div>
	</div>
	</c:forEach>
</body>
</html>