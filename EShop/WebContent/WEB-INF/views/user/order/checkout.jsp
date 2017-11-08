<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- thu vien spring form -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
</head>
<body>
	<h1>CHECKOUT</h1>
	<h2>${message}</h2>
	<form:form action="order/checkout.php" modelAttribute="order">
		<div class="row">
			<div class="form-group col-sm-4">
				<label>Customer</label>
				<form:input path="customer.id" cssClass="form-control" readonly="true"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Order Date</label>
				<form:input path="orderDate" cssClass="form-control" readonly="true"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Required Date</label>
				<form:input path="requireDate" cssClass="form-control" />
			</div>
		</div>

		<div class="row">
			<div class="form-group col-sm-4">
				<label>Receiver</label>
				<form:input path="receiver" cssClass="form-control" />
			</div>
			<div class="form-group col-sm-4">
				<label>Address</label>
				<form:input path="address" cssClass="form-control" />
			</div>
			<div class="form-group col-sm-4">
				<label>Amount</label>
				<form:input path="amount" cssClass="form-control" readonly="true"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-12">
				<label>Description</label>
				<form:textarea path="description" cssClass="form-control" rows="3" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-12">
				<button class="btn btn-default">Purchase</button>
			</div>
		</div>
	</form:form>
</body>
</html>
