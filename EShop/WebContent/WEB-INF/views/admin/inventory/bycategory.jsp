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
	<h1>Inventory By Category</h1>
	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Giá trị'],
          <c:forEach var="a" items="${items}">
      	["${a[0]}",${a[1]}],
      	</c:forEach>
        ]);

        var options = {
          title: 'My Daily Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
	<div id="piechart" style="width: 900px; height: 500px;"></div>

	<c:forEach var="a" items="${items}">
	["${a[0]}",${a[1]}],
	</c:forEach>
	<table class="table">
		<thead>
			<tr>
				<th>Loại</th>
				<th>Giá trị</th>
				<th>Số lượng</th>
				<th>Giá TN</th>
				<th>Giá CN</th>
				<th>Giá TB</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="a" items="${items}">
				<tr>
					<td>${a[0]}</td>
					<td>$<f:formatNumber value="${a[1]}" maxFractionDigits="2"
							minFractionDigits="2" />
					</td>
					<td>${a[2]}</td>
					<td>$<f:formatNumber value="${a[3]}" maxFractionDigits="2"
							minFractionDigits="2" />
					</td>
					<td>$<f:formatNumber value="${a[4]}" maxFractionDigits="2"
							minFractionDigits="2" />
					</td>
					<td>$<f:formatNumber value="${a[5]}" maxFractionDigits="2"
							minFractionDigits="2" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>