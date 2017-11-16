package eshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import eshop.service.ProductService;
import eshop.service.SupplierService;

@Controller
@RequestMapping("admin/inventory")
public class InventoryController {
	@Autowired
	ProductService productService;
	@Autowired
	SupplierService supplierService;
	@RequestMapping("bycategory")
	public String byCategory(ModelMap model) {
		List<Object[]> list= productService.inventoryByCategory();
		model.addAttribute("items",list);
		return "admin/inventory/bycategory";
	}
	@RequestMapping("bysupplier")
	public String bysupplier(ModelMap model) {
		List<Object[]> list= supplierService.inventoryBySupplier();
		model.addAttribute("items",list);
		return "admin/inventory/bysupplier";
	}
}
