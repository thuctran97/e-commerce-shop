package eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import eshop.entity.Category;
import eshop.entity.Product;
import eshop.entity.Supplier;
import eshop.service.CategoryService;
import eshop.service.ProductService;
import eshop.service.SupplierService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	SupplierService supplierService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@RequestMapping("index")
	public String index(ModelMap model) {
		List<Supplier> sup5 = supplierService.get5Items();
		model.addAttribute("sup5", sup5);
		
		List<Product> specials = productService.getSpecialItems();
		model.addAttribute("specials", specials);
		
		List<Category> cat3 = categoryService.get3Items();
		model.addAttribute("cat3", cat3);
		return "home/index";
	}
	@RequestMapping("about")
	public String about() {
		return "user/home/about";
	}
	@RequestMapping("contact")
	public String contact() {
		return "user/home/contact";
	}
	@RequestMapping("feedback")
	public String feedback() {
		return "user/home/feedback";
	}
	@RequestMapping("faq")
	public String faq() {
		return "user/home/faq";
	}
	@ResponseBody
	@RequestMapping("set-lang")
	public String setlang() {
		return "OK";
	}
	
}
