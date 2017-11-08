package eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eshop.entity.Product;
import eshop.service.ShoppingCart;

@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController {
	@Autowired
	ShoppingCart shoppingCart;
	// thêm hàng (product/list.jsp) (khi bấm vào nút xanh ô sản phẩm đang hiển thị trong list
	@ResponseBody
	@RequestMapping("add")
	public String add(@RequestParam("id") Integer id) {
		shoppingCart.add(id); // thêm sp có id vào 
		String json = String.format("[%d, %.2f]", shoppingCart.getCount(),shoppingCart.getAmount());
		//format chuỗi json [count,mount]
		return json;
	}
	//(layout/cart_info.jsp) bấm nút đỏ x trong giỏ hàng đang xem
	@ResponseBody
	@RequestMapping("remove")
	public String remove(@RequestParam("id") Integer id) {
		shoppingCart.remove(id);
		String json = String.format("[%d, %.2f]", shoppingCart.getCount(), shoppingCart.getAmount());
		return json;
	}
	// (layout/cart_info.jsp) update số lượng (khi bấm mũi tên tăng giảm số lượng trong giỏ hàng đang xem)
	@ResponseBody
	@RequestMapping("update")
	public String update(@RequestParam("id") Integer id,
			@RequestParam("qty") Integer qty) {
		
		shoppingCart.update(id, qty);
		Product p = shoppingCart.getItem(id);
		double amount = p.getUnitPrice()*p.getQuantity()*(1-p.getDiscount()); //giá trị để hiện thị nơi giỏ hàng đang xem
		String json = String.format("[%d, %.2f, %.2f]", shoppingCart.getCount(), shoppingCart.getAmount(), amount);
		return json;
	}
	//xem giỏ hàng (layout/cart_info.jsp)
	@RequestMapping("view")
	public String view(ModelMap model) {
		model.addAttribute("cart", shoppingCart);
		return "user/shopping-cart/view";
	}
	//clear giỏ hàng  (layout/cart_info.jsp)
	@RequestMapping("clear")
	public String clear(ModelMap model) {
		shoppingCart.clear();
		model.addAttribute("cart", shoppingCart);
		return "user/shopping-cart/view";
	}
}
