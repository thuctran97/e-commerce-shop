<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
	<script src="js/shopping-cart.js"></script>
</head>
<body>
	<h2>Shopping Cart</h2>
	<table class="table">
		<thead>
			<tr>
				<th>Name</th>
				<th>Unit Price</th>
				<th>Discount</th>
				<th>Quantity</th>
				<th>Amount</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="p" items="${cart.items}"> 
<!-- 		cart từ shopping-cart/view trong ShoppingCartController  -->
<%-- 		${cart.items} -> cart.GetItems() trong ShoppingCart (eshop.service) --%>
			<tr>
				<td>${p.name}</td>
				<td>$<fmt:formatNumber value="${p.unitPrice}"  maxFractionDigits="2" minFractionDigits="2"/></td> 
<!-- 				số chữ số thập phân hiển thị -->
				<td><fmt:formatNumber value="${p.discount}" type="percent" maxFractionDigits="2" minFractionDigits="2"/></td>
				<td><input value="${p.quantity}"  type="number" style="width:50px" data-cart-update="${p.id}"></td>
				<td class="amt">$<fmt:formatNumber value="${p.unitPrice*p.quantity*(1-p.discount)}"  maxFractionDigits="2" minFractionDigits="2"/></td>
				<td>
					<button class="btn btn-sm btn-danger" data-cart-remove="${p.id}">
						<span class="glyphicon glyphicon-trash"></span>  
<!-- 						nút xóa -->
					</button>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<a class="btn btn-warning" href="shopping-cart/clear.php">Clear cart</a>
		<a class="btn btn-info"  href="?">Continue</a>
		<a class="btn btn-primary"  href="order/checkout.php">Checkout</a>
	</div>
</body>
</html>