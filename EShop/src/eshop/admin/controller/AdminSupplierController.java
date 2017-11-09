package eshop.admin.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eshop.entity.Supplier;
import eshop.service.SupplierService;
//supplier có phần upload file
@Controller
@RequestMapping("admin/supplier")
public class AdminSupplierController {
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	ServletContext app;
	//tinh duong dan
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("item", new Supplier());
		return "admin/supplier/index";
	}
	
	@ModelAttribute("items")
	public List<Supplier> getSupplierList() {
		return supplierService.list();
	}
	
	@RequestMapping("insert")
	public String insert(Model model, @ModelAttribute("item") Supplier supplier,
			@RequestParam("uplogo") MultipartFile logo) {
		//x
		
		try {
			if (logo.isEmpty()) {
				supplier.setLogo("supplier.png");
			} else {
				supplier.setLogo(logo.getOriginalFilename());
				String path= app.getRealPath("/images/suppliers/"+supplier.getLogo());
				logo.transferTo(new File(path));
			}
			supplierService.insert(supplier);
			model.addAttribute("message", "Them moi thanh cong");
			return "redirect:/admin/supplier/index.php";
		} 
		catch (Exception e) {
			model.addAttribute("message", "Them moi that bai");
		}
		return "admin/supplier/index";
	}
	
	@RequestMapping("update")
	public String update(Model model, @ModelAttribute("item") Supplier supplier,
			@RequestParam("uplogo") MultipartFile logo) {
		try {
			if (!logo.isEmpty()) {
				supplier.setLogo(logo.getOriginalFilename());
				String path= app.getRealPath("/images/suppliers/"+supplier.getLogo());
				logo.transferTo(new File(path));
			}
			supplierService.update(supplier);
			model.addAttribute("message", "Cap nhat thanh cong");
			return "redirect:/admin/supplier/edit/"+supplier.getId()+".php";
	} 
		catch (Exception e) {
			model.addAttribute("message", "Cap nhat that bai");
		}
		return "admin/supplier/index";
	}
	
	@RequestMapping("delete")
	public String delete(Model model, @ModelAttribute("item") Supplier supplier) {
		try {
			supplierService.delete(supplier);
			model.addAttribute("message", "Xoa thanh cong");
			return "redirect:/admin/supplier/index.php";
		} 
		catch (Exception e) {
			model.addAttribute("message", "Xoa that bai");
		}
		return "admin/supplier/index";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		Supplier supplier = supplierService.get(id);
		model.addAttribute("item", supplier);
		return "admin/supplier/index";
	}
}
