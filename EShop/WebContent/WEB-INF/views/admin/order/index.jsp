<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Lap trinh Java</title>
</head>
<body>
	<h1>Order Manager</h1>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#form"> <span
				class="glyphicon glyphicon-edit"></span> Edition </a></li>
		<li><a data-toggle="tab" href="#gridview"> <span
				class="glyphicon glyphicon-list"></span> List of Items </a></li>
	</ul>

	<div class="tab-content">
		<div id="form" class="tab-pane fade in active">
			<!-- FORM -->
			${message} ${param.message}
			<!-- 	   	message trong attribute -->
			<!-- 	   	param.message trong param 	 -->
			<form:form action="admin/order/index.php" modelAttribute="item">
				<div class="row">
					<div class="form-group col-sm-4">
						<label>Id</label>
						<form:input path="id" cssClass="form-control" readonly="true" />
					</div>
					<div class="form-group col-sm-4">
						<label>CustomerId</label>
						<form:input path="customer.id" cssClass="form-control"
							readonly="true" />
					</div>
					<div class="form-group col-sm-4">
						<label>OrderDate</label>
						<form:input path="orderDate" cssClass="form-control" />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-4">
						<label>RequireDate</label>
						<form:input path="requireDate" cssClass="form-control" />
					</div>
					<div class="form-group col-sm-4">
						<label>Receiver</label>
						<form:input path="receiver" cssClass="form-control" />
					</div>
					<div class="form-group col-sm-4">
						<label>Address</label>
						<form:input path="address" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group col-sm-4">
					<label>Amount</label>
					<form:input path="amount" cssClass="form-control" />
				</div>
				<div class="row">
					<c:if test="${!empty  item.orderDetails}">
						<fieldset>
							<legend>ORDER DETAILS</legend>
							<table class="table">
								<thead>
									<tr>
										<th>Name</th>
										<th>Unit Price</th>
										<th>Discount</th>
										<th>Quantity</th>
										<th>Amount</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="d" items="${item.orderDetails}">
										<tr>
											<td>${d.product.name}</td>
											<td>${d.unitPrice}</td>
											<td>${d.discount}</td>
											<td>${d.quantity}</td>
											<td>${d.unitPrice*d.quantity*(1-d.discount)}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</fieldset>

					</c:if>
				</div>
				<div class="row">
					<div class="form-group col-sm-12">
						<label>Description</label>
						<form:textarea path="description" cssClass="form-control" />
					</div>
					<div class="form-group">

						<button class="btn btn-warning"
							formaction="admin/order/update.php">
							<span class="glyphicon glyphicon-save"></span> Update
						</button>
						<button class="btn btn-danger" formaction="admin/order/delete.php">
							<span class="glyphicon glyphicon-trash"></span> Delete
						</button>
						<button class="btn btn-info" formaction="admin/order/index.php">
							<span class="glyphicon glyphicon-refresh"></span> Reset
						</button>
					</div>
				</div>
			</form:form>
		</div>
		<div id="gridview" class="tab-pane fade">
			<!-- TABLE -->
			
		</div>
	</div>
</body>
</html>