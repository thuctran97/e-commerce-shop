package eshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import eshop.entity.Order;
import eshop.service.OrderService;
//copy tu admincategorycontroller
@Controller
@RequestMapping("admin/order")
public class AdminOrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("item", new Order());
		return "admin/order/index";
	}
	
	@ModelAttribute("items")
	public List<Order> getOrderList() {
		return orderService.list();
	}
	
	@RequestMapping("insert")
	public String insert(Model model, @ModelAttribute("item") Order order) {
		try {
			orderService.insert(order);
			model.addAttribute("message", "Them moi thanh cong");
			return "redirect:/admin/order/index.php";
		} 
		catch (Exception e) {
			model.addAttribute("message", "Them moi that bai");
		}
		return "admin/order/index";
	}
	
	@RequestMapping("update")
	public String update(Model model, @ModelAttribute("item") Order order) {
		try {
			orderService.update(order);
			model.addAttribute("message", "Cap nhat thanh cong");
			return "redirect:/admin/order/edit/"+order.getId()+".php";
	} 
		catch (Exception e) {
			model.addAttribute("message", "Cap nhat that bai");
		}
		return "admin/order/index";
	}
	
	@RequestMapping("delete")
	public String delete(Model model, @ModelAttribute("item") Order order) {
		try {
			orderService.delete(order);
			model.addAttribute("message", "Xoa thanh cong");
			return "redirect:/admin/order/index.php";
		} 
		catch (Exception e) {
			model.addAttribute("message", "Xoa that bai");
		}
		return "admin/order/index";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Order order = orderService.get(id);
		model.addAttribute("item", order);
		return "admin/order/index";
	}
}
