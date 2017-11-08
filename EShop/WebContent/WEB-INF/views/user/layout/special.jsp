<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div class="panel panel-default">
	<div class="panel-heading">
		<span class="glyphicon glyphicon-star-empty"></span> <strong>  <s:message code="g.spec.title"/></strong>
	</div>

	<div class="list-group">
		<a href="product/list-by-special/0.php" class="list-group-item">  <s:message code="g.spec.best"/></a> 
		<a href="product/list-by-special/1.php" class="list-group-item"> <s:message code="g.spec.latest"/></a> 
		<a href="product/list-by-special/2.php" class="list-group-item"> <s:message code="g.spec.saleoff"/></a> 
		<a href="product/list-by-special/3.php" class="list-group-item"> <s:message code="g.spec.spec"/></a> 
		<a href="product/list-by-special/4.php" class="list-group-item"> <s:message code="g.spec.view"/></a>
	</div>
</div>
