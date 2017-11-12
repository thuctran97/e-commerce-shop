<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
	<script>
	$(function(){
		$("#roleId").change(function(){
			var rid = $(this).val();
			$.ajax({
				url:"admin/actionrole/get-web-action-ids.php",
				data:{roleId: rid},
				dataType:"json",
				success:function(response){
					$(":checkbox").prop("checked", false);
					for(var i=0;i<response.length;i++){
						var r = response[i];
						$(":checkbox[value="+r+"]").prop("checked", true);	
					}
				}
			});
		});
		$("#roleId").change();
		$(":checkbox").click(function(){
			var aid = $(this).val();
			var rid = $("#roleId").val();
			$.ajax({
				url:"admin/actionrole/insert-or-delete.php",
				data:{"webAction.id":aid, "role.id":rid}
			});
		});
	});
	</script>
</head>
<body>
	<h1>Web Action Role Manager</h1>
	<fieldset>
		<label>Role: </label><br>
		<select id="roleId">
			<c:forEach var="r" items="${roles}">
				<option value="${r.id}">${r.name}</option>
			</c:forEach>
		</select>
	</fieldset>
	
	<fieldset>
		<label>WebAction:</label>
		<ul class="list-unstyled">
		<c:forEach var="w" items="${webactions}">
			<li class="col-sm-4">
				<label>
					<input type="checkbox" value="${w.id}">${w.name}
				</label>
			</li>
		</c:forEach>
	</ul>
	</fieldset>
</body>
</html>