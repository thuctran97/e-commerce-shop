<%@ page pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/"/>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Shopping Mall</title>
	
	<!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    
    <!-- jQuery Validation kiểm lỗi -->
    <script src="js/jquery.validate.min.js"></script>
	
	<!-- jQuery UI hiệu ứng giỏ hàng bay-->
    <link href="css/jquery-ui.min.css" rel="stylesheet" />
    <script src="js/jquery-ui.min.js"></script>
	
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <script src="js/bootstrap.min.js"></script>
	
	<!-- eShop -->
    <link href="css/eshop.css" rel="stylesheet" />
    
</head>
<body>
    <div class="container">
        <header class="nn-header row">
        	<div class="nn-company">
	            <h1>Online shopping mall</h1>
	            <h5>The center point of the professional programming</h5>
            </div>
            <img class="pull-right" src="images/header-object.png" />
        </header>

       <jsp:include page="user/layout/menu.jsp"></jsp:include>

        <div class="nn-sheet row">

            <article class="col-sm-9">
                <div class="nn-body">
                	<jsp:include page="${param.view}"></jsp:include>
                </div>
            </article>

            <aside class="col-sm-3">
                <!--CartInfo-->
                 <jsp:include page="user/layout/cart_info.jsp"></jsp:include>
                <!--/CartInfo-->

                <!--Search-->
                <jsp:include page="user/layout/search.jsp"></jsp:include>
                <!--/Search-->

                <!--Category-->
                 <jsp:include page="user/layout/category.jsp"></jsp:include>
                <!--/Category-->

                <!--Supplier-->
                <jsp:include page="user/layout/supplier.jsp"></jsp:include>
                <!--/Supplier-->

                <!--Special-->
                <jsp:include page="user/layout/special.jsp"></jsp:include>
                <!--/Special-->

            </aside>
        </div>

        <footer class="panel panel-default row">
	        <div class="panel-heading">
	        	<p class="text-center">Designed by Thuc.</p>
	        </div>
        </footer>
    </div>
</body>
</html>