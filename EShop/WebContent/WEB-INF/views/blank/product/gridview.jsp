<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<table class="table">
	<thead>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Unit Price</th>
			<th>Unit Brief</th>
			<th>Quantity</th>
			<th>Discount</th>
			<th>Category</th>
			<th>Supplier</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="o" items="${items}">
			<tr>
				<td>${o.id}</td>
				<td>${o.name}</td>
				<td>${o.unitPrice}</td>
				<td>${o.unitBrief}</td>
				<td>${o.quantity}</td>
				<td>${o.discount}</td>
				<td>${o.category.nameVN}</td>
				<td>${o.supplier.name}</td>
				<td><a href="admin/product/edit/${o.id}.php"
					class="btn btn-sm btn-default"> <span
						class="glyphicon glyphicon-edit"></span> Edit
				</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>