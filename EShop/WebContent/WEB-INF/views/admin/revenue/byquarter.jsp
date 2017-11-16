<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
</head>
<body>
	<h1>Revenue By Quarter</h1>
	<table class="table">
	<thead>
		<tr>
			<th>Quý</th>
			<th>Doanh số</th>
			<th>SL bán</th>
			<th>Giá bán TN</th>
			<th>Giá bán CN</th>
			<th>Giá bán TB</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="a" items="${items}">
		<tr>
			<td><f:formatNumber value="${a[0]}" maxFractionDigits="0" minFractionDigits="0"/></td>
			<td>$<f:formatNumber value="${a[1]}" maxFractionDigits="2" minFractionDigits="2"/></td>
			<td>${a[2]}</td>
			<td>$<f:formatNumber value="${a[3]}" maxFractionDigits="2" minFractionDigits="2"/></td>
			<td>$<f:formatNumber value="${a[4]}" maxFractionDigits="2" minFractionDigits="2"/></td>
			<td>$<f:formatNumber value="${a[5]}" maxFractionDigits="2" minFractionDigits="2"/></td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
</body>
</html>