<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="navbar navbar-inverse row">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
	</div>
	<div class="navbar-collapse collapse">
		<!--Menu-->
		<ul class="nav navbar-nav">
			<li><a href="home/index.php"> <span
					class="glyphicon glyphicon-home"> </span> <s:message code="g.home" />
			</a></li>
			<li><a href="home/about.php"> <span
					class="glyphicon glyphicon-th-list"></span> <s:message
						code="g.about" />
			</a></li>
			<li><a href="home/contact.php"> <span
					class="glyphicon glyphicon-earphone"></span> <s:message
						code="g.contact" />
			</a></li>
			<li><a href="home/feedback.php"><span
					class="glyphicon glyphicon-envelope"></span> <s:message
						code="g.feedback" /> </a></li>
			<li><a href="home/faq.php"><span
					class="glyphicon glyphicon-question-sign"></span> <s:message
						code="g.faq" /> </a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
					<s:message code="g.account" /> <span class="caret"></span> </a>
				<ul class="dropdown-menu">
					<c:choose>
						<c:when test="${empty sessionScope.user}">
							<li><a href="account/login.php"> <s:message
										code="g.a.login" />
							</a></li>
							<li><a href="account/forgot.php""> <s:message
										code="g.a.forgot" />
							</a></li>
							<li><a href="account/register.php""> <s:message
										code="g.a.register" />
							</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="account/logoff.php""> <s:message
										code="g.a.logoff" />
							</a></li>
							<li><a href="account/change.php""> <s:message
										code="g.a.change" />
							</a></li>
							<li><a href="account/edit.php""> <s:message
										code="g.a.edit" />
							</a></li>
							<li class="divider"></li>
							<li><a href="order/list.php""> <s:message
										code="g.a.order" />
							</a></li>
							<li><a href="order/items.php""> <s:message
										code="g.a.items" />
							</a></li>
						</c:otherwise>
					</c:choose>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#" data-lang="en">English</a></li>
			<li><a href="#" data-lang="vi">Tiếng Việt</a></li>
		</ul>
		<!--Menu-->
	</div>
</div>
<script>
	$(function() {
		$("a[data-lang]").click(function() {
			var lang = $(this).attr("data-lang");
			$.ajax({
				url : "home/set-lang.php",
				data : {
					lang : lang
				},
				success : function(response) {
					location.reload();
				}
			});
			return false;
		});
	})

	
</script>