<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="cart" value="${sessionScope['scopedTarget.cart']}"/> 

<!-- Truy cập vào session scope bean tên là cart (componet cart)  -->

<div class="nn-cart">
	<div class="panel panel-default">
		<div class="panel-body">
			<img class="col-sm-5" src="images/shoppingcart.gif" />
			<ul class="col-sm-7">
				<li><span id="count">${cart.count}</span> <s:message code="g.cart.items"/></li>
				<li>$ <span id="amount">${cart.amount}</span></li>
				
<%-- 				${cart.count} -> cart.getCount (ShoppingCart trong eshop.service) --%>
<!-- 				khác cart trong view -->

				<li><a href="shopping-cart/view.php"><s:message code="g.cart.view"/></a>
				</li>
			</ul>
		</div>
	</div>
</div>