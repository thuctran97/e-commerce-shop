<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<span class="glyphicon glyphicon-th-list"></span> <strong> <s:message
				code="g.cate.title" />
		</strong>
	</div>

	<div class="list-group">
		<c:forEach var="c" items="${cates}">
			<a href="product/list-by-category/${c.id}.php" class="list-group-item">${c.nameVN}</a>
		</c:forEach>
	</div>
</div>

