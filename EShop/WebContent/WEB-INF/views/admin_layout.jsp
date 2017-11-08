<%@ page pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/"/>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Website Administration</title>

	<!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    
    <!-- jQuery Validation -->
    <script src="js/jquery.validate.min.js"></script>
	
	<!-- jQuery UI -->
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
        <header class="nn-header row" style="height: 90px;">
            <div class="nn-company">
	            <h1>Website administration tool</h1>
            </div>
        </header>

       <jsp:include page="admin/layout/menu.jsp"/>

        <div class="nn-sheet row">
            <article class="nn-body">
                <jsp:include page="${param.view}"></jsp:include>
            </article>
        </div>

        <footer class="panel panel-default row">
	        <div class="panel-heading">
	        	<p class="text-center">Nhất Nghệ &copy; 2017. All rights reserved.</p>
	        </div>
        </footer>
    </div>
</body>
</html>