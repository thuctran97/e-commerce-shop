$(function() {
	$("button[data-cart-add]").click(function() {
		var id= $(this).attr("data-cart-add");
			$.ajax({
				url:"shopping-cart/add.php",
				data:{id: id},
				success: function(response){
					$(".nn-cart #count").html(response[0]);
					$(".nn-cart #amount").html(response[1]);
				},
				dataType:"json"				
			});
	});
});
/*XOA KHOI GIO HANG*/
$(function() {
	$("button[data-cart-remove]").click(function() {
		var id= $(this).attr("data-cart-remove");
			$.ajax({
				url:"shopping-cart/remove.php",
				data:{id: id},
				success: function(response){
					$(".nn-cart #count").html(response[0]);
					$(".nn-cart #amount").html(response[1]);
				},
				dataType:"json"				
			});
		$(this).parents("tr").hide(300);
//		Tìm ra thẻ cha của nút đang click (đang xảy ra) -> ẩn trong 300milisecond
	});
});

$(function() {
	$("input[data-cart-update]").click(function() {
		var id= $(this).attr("data-cart-update");
			$.ajax({
				url:"shopping-cart/update.php",
				data:{id: id},
				success: function(response){
					$(".nn-cart #count").html(response[0]);
					$(".nn-cart #amount").html(response[1]);
				},
				dataType:"json"				
			});
	});
