<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
	<script src="slideshow/js/modernizr.custom.63321.js"></script>
    <script src="slideshow/js/jquery.catslider.js"></script>
    <link href="slideshow/css/catslider.css" rel="stylesheet" />
    
<!--     special items -->
    <link href="css/product.css" rel="stylesheet">	
    <script src="js/shopping-cart.js"></script>
    <style>
        .mi-slider {
            height: 330px;
        }
        .mi-slider ul li img {
            height: 200px;
        }
    </style>
    <script>
        $(function () {
            showCatSlider('.mi-slider', 5000);
        });
    </script>
</head>
<body>
	<!-- Slideshow -->
	<div class="mi-slider">
		<c:forEach var="s" items="${sup5}">
        <ul>
        	<c:forEach var="p"  items="${s.products}" end="3">
            <li>
                <a href="product/detail/${p.id}.php">
                    <img src="images/products/${p.image}" />
                    <h4>${p.unitPrice}</h4>
                </a>
            </li>
            </c:forEach>
        </ul>
        </c:forEach>
        <nav>
        <c:forEach var="s" items="${sup5}">
            <a href="#">${s.name}</a>
        </c:forEach>
        </nav>
    </div>
	
	<!-- Special Items -->
	
	
	
	<c:forEach var="p" items="${specials}">
	<div class="col-sm-3 nn-box">
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
		</div>
	</div>
	</c:forEach>
	
	<!-- 3 Random Categories -->
	<c:forEach var="c" items="${cat3}">
		<div class="col-sm-4">
			<fieldset>
				<legend>${c.nameVN}</legend>
				<ul>
				<c:forEach var="p" items="${c.products}" end="4">
					<li>
						<a href="product/detail/${p.id}.php">${p.name}</a>
					</li>
				</c:forEach>
				</ul>
			</fieldset>
		</div>
	</c:forEach>
</body>
</html>