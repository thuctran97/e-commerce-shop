<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="panel panel-default">

	<div class="panel-heading">
		<span class="glyphicon glyphicon-list-alt"></span> <strong> <s:message code="g.sup.title"/></strong>
	</div>

	<div class="list-group">
		<c:forEach var="s" items="${supps}">
		<a href="product/list-by-supplier/${s.id}.php" class="list-group-item">${s.name}</a>
		</c:forEach> 
	</div>
</div>

