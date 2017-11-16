package eshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import eshop.service.OrderDetailService;

@Controller
@RequestMapping("admin/revenue")
public class RevenueController {
	@Autowired
	OrderDetailService orderDetailService;
	@RequestMapping("byproduct")
	public String byproduct(ModelMap model) {
		List<Object[]> list= orderDetailService.revenueByProduct();
		model.addAttribute("items",list);
		return "admin/revenue/byproduct";
	}
	@RequestMapping("bycategory")
	public String bycategory(ModelMap model) {
		List<Object[]> list= orderDetailService.revenueByCategory();
		model.addAttribute("items",list);
		return "admin/revenue/bycategory";
	}
	@RequestMapping("bysupplier")
	public String bysupplier(ModelMap model) {
		List<Object[]> list= orderDetailService.revenueBySupplier();
		model.addAttribute("items",list);
		return "admin/revenue/bysupplier";
	}
	@RequestMapping("bycustomer")
	public String bycustomer(ModelMap model) {
		List<Object[]> list= orderDetailService.revenueByCustomer();
		model.addAttribute("items",list);
		return "admin/revenue/bycustomer";
	}
	@RequestMapping("byyear")
	public String byyear(ModelMap model) {
		List<Object[]> list= orderDetailService.revenueByYear();
		model.addAttribute("items",list);
		return "admin/revenue/byyear";
	}
	@RequestMapping("byquarter")
	public String byquarter(ModelMap model) {
		List<Object[]> list= orderDetailService.revenueByQuarter();
		model.addAttribute("items",list);
		return "admin/revenue/byquarter";
	}
	@RequestMapping("bymonth")
	public String bymonth(ModelMap model) {
		List<Object[]> list= orderDetailService.revenueByMonth();
		model.addAttribute("items",list);
		return "admin/revenue/bymonth";
	}
}
