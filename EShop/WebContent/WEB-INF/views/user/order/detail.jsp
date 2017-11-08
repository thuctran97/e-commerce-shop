<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
</head>
<body>
	<h1>Order Information</h1>
	<form:form action="order/checkout.php" modelAttribute="order">
	<div class="row">
		<div class="form-group col-sm-4">
			<label>Customer</label>
			<form:input path="customer.id" cssClass="form-control" readonly="true"/>
		</div>
		
		<div class="form-group col-sm-4">
			<label>Receiver</label>
			<form:input path="receiver" cssClass="form-control" readonly="true"/>
		</div>
		
		<div class="form-group col-sm-4">
			<label>Order Date</label>
			<form:input path="orderDate" cssClass="form-control" readonly="true"/>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-sm-4">
			<label>Required Date</label>
			<form:input path="requireDate" cssClass="form-control" readonly="true"/>
		</div>
		
		<div class="form-group col-sm-4">
			<label>Address</label>
			<form:input path="address" cssClass="form-control" readonly="true"/>
		</div>
		
		<div class="form-group col-sm-4">
			<label>Amount</label>
			<form:input path="amount" cssClass="form-control" readonly="true"/>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-sm-12">
			<label>Notes</label>
			<form:textarea path="description" rows="5" cssClass="form-control" readonly="true"/>
		</div>
	</div>
	</form:form>
	<fieldset>
		<legend>Order Details</legend>
		<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Unit Price</th>
				<th>Discount</th>
				<th>Quantity</th>
				<th>Amount</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="d" items="${order.orderDetails}">
<!-- 		order.getOrderDetails -->
			<tr>
				<td>${d.id}</td>
				<td>${d.product.name}</td>
				<td>${d.unitPrice}</td>
				<td>${d.discount*100}%</td>
				<td>${d.quantity}</td>
				<td>${d.unitPrice*d.quantity*(1-d.discount)}</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</fieldset>
</body>
</html>