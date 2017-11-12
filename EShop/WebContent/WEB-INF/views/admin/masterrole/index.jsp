<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lap trinh Java</title>
	<script>
	$(function(){
		/*
		 * check vào checkbox các role của manager đang hiển thị 
		 */
		$("#masterId").change(function(){ //khi thay đổi masterId
			var mid = $(this).val();
			$.ajax({
				url:"admin/masterrole/get-role-ids.php",
				data:{masterId: mid},
				//gửi masterId về server
				dataType:"json",
				success:function(response){
					$(":checkbox").prop("checked", false);
					//uncheck hết tất cả checkbox 
					for(var i=0;i<response.length;i++){
						var roleId = response[i];
						$(":checkbox[value="+roleId+"]").prop("checked", true);	
					}
					//check các role có roleId trong json nhận về
				}
			});
		});
		$("#masterId").change(); //chọn select box đầu tiên
		//check / uncheck 1 role nào đó
		$(":checkbox").click(function(){
			var roleId = $(this).val();
			var masterId = $("#masterId").val();
			$.ajax({
				url:"admin/masterrole/update.php",
				data:{"role.id": roleId, "master.id": masterId},
				//đưa về server 2 thuộc tính role.id và master.id -> update nhận masterRole luôn
			});
		});
	});
	</script>
</head>
<body>
	<h1>Master Role Manager</h1>
	<fieldset>
		<label>User: </label><br>
		<select id="masterId">
			<c:forEach var="m" items="${masters}">
				<option value="${m.id}">${m.fullName}</option>
			</c:forEach>
		</select>
	</fieldset>
	
	<fieldset>
		<label>Roles:</label>
		<ul class="list-unstyled">
		<c:forEach var="r" items="${roles}">
			<li>
				<label>
					<input type="checkbox" value="${r.id}">
					${r.name}
				</label>
			</li>
		</c:forEach>
	</ul>
	</fieldset>
</body>
</html>