$(function(){
	/*
	 * Bo vao gio hang 
	 */
	$("button[data-cart-add]").click(function(){
		var id = $(this).attr("data-cart-add");
//		giá trị id sp đang bấm
		$.ajax({
			url:"shopping-cart/add.php",
			data:{id: id}, 
//			tham số id gửi đến server
			success:function(response){
				$(".nn-cart #count").html(response[0]); 
				//Tìm đến thẻ trong class = nn-cart, co id=count -> trả về giá trị nằm ở ô giỏ hàng bên phải trong Trang hiển thị (layout/cart_info)
				$(".nn-cart #amount").html(response[1]);
			},
			dataType:"json"
		});
	});
	
	/*
	 * Xoa khoi gio hang 
	 */
	$("button[data-cart-remove]").click(function(){
		var id = $(this).attr("data-cart-remove");
		$.ajax({
			url:"shopping-cart/remove.php",
			data:{id: id},
			success:function(response){
				$(".nn-cart #count").html(response[0]);  //Trả về 2 giá trị nằm ở ô giỏ hàng bên phải trong Trang hiển thị (layout/cart_info)
				$(".nn-cart #amount").html(response[1]);
			},
			dataType:"json"
		});
		$(this).parents("tr").hide(300);
//		Tìm ra thẻ cha của nút đang click (thẻ this là button -> thẻ cha của button nằm trong phạm vi thẻ tr -> thẻ td)
//		-> ẩn trong 300milisecond
	});
	
	/*
	 * Xoa khoi gio hang 
	 */
	$("input[data-cart-update]").change(function(){
		var id = $(this).attr("data-cart-update");
		var qty = $(this).val();
		td_amt = $(this).parents("tr").find(".amt");
		//thẻ cha của this, phạm vi trong thẻ tr có class=amt
		$.ajax({
			url:"shopping-cart/update.php",
			data:{id: id, qty: qty},
//			gửi 2 tham số id và qty về server
			success:function(response){
				$(".nn-cart #count").html(response[0]);
				$(".nn-cart #amount").html(response[1]);
				td_amt.html("$"+response[2]); // trả về amount nơi giỏ hàng đang xem
			},
			dataType:"json"
		});
	});
});