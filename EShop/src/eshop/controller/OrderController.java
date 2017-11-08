package eshop.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eshop.entity.Customer;
import eshop.entity.Order;
import eshop.entity.Product;
import eshop.service.OrderService;
import eshop.service.ShoppingCart;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	ShoppingCart shoppingCart;
	@Autowired
	OrderService orderService;
	@RequestMapping("checkout")
	public String checkout(ModelMap model, HttpSession httpSession) {
		Customer user = (Customer)httpSession.getAttribute("user");
		//tài khoản người đăng nhập-> lấy từ session ra
		Order order = new Order();
		order.setCustomer(user);
		order.setAmount(shoppingCart.getAmount());
		order.setOrderDate(new Date());
		Date date = new Date();
		date.setDate(date.getDate()+2); //ngay co hang sau ngay hien tai 2 ngay
		order.setRequireDate(date);
		order.setReceiver(user.getFullname());
		model.addAttribute("order",order); 
		return "user/order/checkout";
	}
	//khi bam vao purchange
	@RequestMapping(value="checkout",method=RequestMethod.POST)
	public String checkout(ModelMap model,@ModelAttribute("order")Order order) {
		try {
			orderService.purchase(order,shoppingCart); //lưu order và orderDetail vào session
			shoppingCart.clear();
			model.addAttribute("message","Đặt hàng thành công");
			return "redirect:/order/detail/"+order.getId()+".php";
			//xem lai thong tin don hang
		} catch (Exception e) {
			model.addAttribute("message","Đặt hàng thất bại");
		}
	
		return "user/order/checkout";
	}
	@RequestMapping("detail/{id}")
	public String detail(@PathVariable("id") Integer id, ModelMap model) {
		Order order = orderService.get(id);
		model.addAttribute("order",order);
		return "user/order/detail";
	}

	@RequestMapping("list")
	public String list(ModelMap model, HttpSession session) {
		Customer user = (Customer) session.getAttribute("user");
		List<Order> list = orderService.getOrderListByUser(user);
		model.addAttribute("orders", list);
		return "user/order/list";
	}
	@RequestMapping("items")
	public String items(ModelMap model, HttpSession session) {
		Customer user = (Customer) session.getAttribute("user");
		List<Product> list = orderService.getPurchasedItems(user);
		model.addAttribute("prods", list); 
		//su dung gian dien product -> list.jsp
		return "user/product/list";
	}
	
}
