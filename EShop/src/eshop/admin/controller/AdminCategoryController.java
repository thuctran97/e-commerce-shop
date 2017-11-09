package eshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eshop.entity.Category;
import eshop.service.CategoryService;

@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("item", new Category());
		//category mới trống rỗng -> trên form không có gì
		return "admin/category/index";
	}
	
	@ModelAttribute("items")
	public List<Category> getCategoryList() {
		return categoryService.list();
	}
	
	@RequestMapping("insert")
	public String insert(Model model, @ModelAttribute("item") Category category) {
		try {
			categoryService.insert(category);
			model.addAttribute("message", "Them moi thanh cong");
			//thêm xong về lại action admin/category/index -> trống rỗng
			//click lên link để hiểu
			return "redirect:/admin/category/index.php";
		} 
		catch (Exception e) {
			model.addAttribute("message", "Them moi that bai");
		}
		return "admin/category/index";
	}
	
	@RequestMapping("update")
	public String update(Model model, @ModelAttribute("item") Category category) {
		try {
			categoryService.update(category);
			model.addAttribute("message", "Cap nhat thanh cong");
			//update xong về lại action admin/category/edit/xxx.php
			//click lên link để hiểu
			return "redirect:/admin/category/edit/"+category.getId()+".php";
	} 
		catch (Exception e) {
			model.addAttribute("message", "Cap nhat that bai");
		}
		return "admin/category/index";
	}
	
	@RequestMapping("delete")
	public String delete(Model model, @ModelAttribute("item") Category category) {
		try {
			categoryService.delete(category);
			model.addAttribute("message", "Xoa thanh cong");
			return "redirect:/admin/category/index.php";
		} 
		catch (Exception e) {
			model.addAttribute("message", "Xoa that bai");
		}
		return "admin/category/index";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Category category = categoryService.get(id);
		model.addAttribute("item", category);
		return "admin/category/index";
	}
	
}
