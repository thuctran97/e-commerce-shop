<%@ page pageEncoding="utf-8"%>
<div>
	<div class="panel panel-default">
		<div class="panel-body">
			<form method="post" action="product/search.php">
				<input value="${param.Keywords}" placeholder="Keywords" name="Keywords" class="form-control" />
			</form>
		</div>
	</div>
</div>