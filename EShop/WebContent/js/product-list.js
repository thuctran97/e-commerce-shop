//views/admin/product/index.jsp
//<div id="gridview" class="tab-pane fade">
//		<ul class="pager">
//	    	0 <li><a href="#"><span class="glyphicon glyphicon-hand-up"></span></a></li> 	
//	    	1 <li><a href="#"><span class="glyphicon glyphicon-hand-left"></span></a></li>
//	    	2 <li><a href="#">1/22</a></li>
//	    	3 <li><a href="#"><span class="glyphicon glyphicon-hand-right"></span></a></li>
//	    	4 <li><a href="#"><span class="glyphicon glyphicon-hand-down"></span></a></li>
//	    </ul>
// 		<div id="page"></div>
//</div>
//Chạy ajax đến url -> các action trong AdminProductController -> response 

$(function() {
	pageNo = 0;
	pageSize = 6;
	pageCount = 10;

	// Tai tong so trang
	$.ajax({
		url : "admin/product/pagecount.php",
		data : {
			pageSize : pageSize  //gửi 1 tham số đến server (action pagecount (AdminProductController))
		},
		success : function(response) {
			pageCount = parseInt(response);   //getPagecount (AdminProductController)
		}
	});

	function gotoPage() {
		$.ajax({
			url : "admin/product/loadpage.php",
			data : {
				pageNo : pageNo,
				pageSize : pageSize
			},
			success : function(response) {
				$("#page").html(response); //dữ liệu bảng được truyền
				$(".pager a:eq(2)").html((1 + pageNo) + " of " + pageCount);  
				//class pager dòng a 2 (thứ 3)
			}
		});
	}

	gotoPage(); // hiển thị trang đầu tiên khi mới vào xem list

	$(".pager a:eq(0)").click(function() {
		pageNo = 0;
		gotoPage();
		return false;
	});

	$(".pager a:eq(1)").click(function() {
		if (pageNo > 0) {
			pageNo -= 1;
			gotoPage()
		}
		return false;
	});

	$(".pager a:eq(3)").click(function() {
		if (pageNo < pageCount - 1) {
			pageNo += 1;
			gotoPage()
		}
		return false;
	});

	$(".pager a:eq(4)").click(function() {
		pageNo = pageCount - 1;
		gotoPage();
		return false;
	});
});