<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Lap trinh Java</title>
</head>
<body>
	<h1>Order List</h1>
	<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Order Date</th>
				<th>Required Date</th>
				<th>Receiver</th>
				<th>Amount</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="o" items="${orders}">
				<tr>
					<td>#${o.id}</td>
					<td><f:formatDate value="${o.orderDate}" pattern="dd-MM-yyyy" /></td>
					<td>${o.requireDate}</td>
					<td>${o.receiver}</td>
					<td>$<f:formatNumber value="${o.amount}" maxFractionDigits="2" /> </td>
					<td><a href="order/detail/${o.id}.php">Detail</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>